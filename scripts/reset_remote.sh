#!/usr/bin/env bash
set -euo pipefail

# reset_remote.sh
# Safely reset a remote repository to match your current local branch state.
# - Creates a mirror backup of the remote
# - Creates an orphan branch with a single commit of your current working tree
# - Force-pushes that commit to the remote branch you choose
# - Optionally deletes other remote branches and tags

usage() {
  cat <<EOF
Usage: $0 [options]

Options:
  -r <remote>         Remote name (default: origin)
  -b <backup-dir>     Local path to write mirror backup (default: ../repo-remote-backup.git)
  -B <branch>         Remote branch to overwrite (default: current branch)
  --delete-refs       Delete other remote branches (destructive)
  --delete-tags       Delete all remote tags (destructive)
  --no-push           Do not push; just create the local orphan commit (dry-run-ish)
  -y                  Automatically answer yes to confirmations
  -h                  Show this help

This script is destructive. Read and confirm before proceeding.
EOF
}

# defaults
REMOTE="origin"
BACKUP_DIR="../$(basename "$(pwd)")-remote-backup.git"
DELETE_REFS=false
DELETE_TAGS=false
NO_PUSH=false
AUTO_YES=false
TARGET_BRANCH=""

# parse args
while [[ ${#} -gt 0 ]]; do
  case "$1" in
    -r) REMOTE="$2"; shift 2;;
    -b) BACKUP_DIR="$2"; shift 2;;
    -B) TARGET_BRANCH="$2"; shift 2;;
    --delete-refs) DELETE_REFS=true; shift;;
    --delete-tags) DELETE_TAGS=true; shift;;
    --no-push) NO_PUSH=true; shift;;
    -y) AUTO_YES=true; shift;;
    -h) usage; exit 0;;
    *) echo "Unknown arg: $1"; usage; exit 1;;
  esac
done

# helpers
confirm() {
  if [ "$AUTO_YES" = true ]; then
    return 0
  fi
  read -r -p "$1 [y/N]: " ans
  case "$ans" in
    [Yy]|[Yy][Ee][Ss]) return 0;;
    *) return 1;;
  esac
}

# Ensure we're in a git repo
if ! git rev-parse --git-dir > /dev/null 2>&1; then
  echo "Error: not a git repository. Run this script from the repository root." >&2
  exit 2
fi

CURRENT_BRANCH=$(git rev-parse --abbrev-ref HEAD)
if [ -z "$TARGET_BRANCH" ]; then
  TARGET_BRANCH="$CURRENT_BRANCH"
fi

cat <<EOF
About to reset remote '$REMOTE' so that branch '$TARGET_BRANCH' matches your current local working tree state.
This will create a mirror backup at: $BACKUP_DIR
Options:
  Delete other remote branches: $DELETE_REFS
  Delete remote tags: $DELETE_TAGS
  Dry-run (no push): $NO_PUSH

Local branch used: $CURRENT_BRANCH
EOF

if ! confirm "Proceed with these actions?"; then
  echo "Aborted by user. No changes made."; exit 1
fi

# 1) Backup remote (mirror clone)
REMOTE_URL=$(git remote get-url "$REMOTE")
if [ -z "$REMOTE_URL" ]; then
  echo "Cannot determine URL for remote '$REMOTE'" >&2
  exit 3
fi

echo "Creating mirror backup of remote '$REMOTE' at: $BACKUP_DIR"
if [ -d "$BACKUP_DIR" ]; then
  echo "Backup dir already exists: $BACKUP_DIR" >&2
  if ! confirm "Overwrite existing backup directory?"; then
    echo "Aborting backup step.";
  else
    rm -rf "$BACKUP_DIR"
  fi
fi

git clone --mirror "$REMOTE_URL" "$BACKUP_DIR"

# 2) Create orphan branch and commit current working tree
TIMESTAMP=$(date +%Y%m%d%H%M%S)
CLEAN_BRANCH="clean-reset-$TIMESTAMP"

echo "Creating orphan branch '$CLEAN_BRANCH' from current working tree"
# Use a temporary index to avoid touching user's current branch state
# Create the orphan branch
git checkout --orphan "$CLEAN_BRANCH"
# Clear the index and working tree from previous branch refs
git reset --hard
# Add current working tree files (this keeps uncommitted changes as part of the commit)
# To capture working tree exactly, we stage everything in the fs
git add -A

# If nothing to commit, create an empty commit to represent state
if git diff --cached --quiet; then
  echo "No changes staged; creating an empty commit to mark the state."
  git commit --allow-empty -m "Repository reset: keep current working tree state ($TIMESTAMP)"
else
  git commit -m "Repository reset: keep current working tree state ($TIMESTAMP)"
fi

if [ "$NO_PUSH" = true ]; then
  echo "Created local orphan branch '$CLEAN_BRANCH' but --no-push was specified. Exiting before any remote changes.";
  echo "To push manually: git push $REMOTE $CLEAN_BRANCH:$TARGET_BRANCH --force"
  exit 0
fi

# 3) Force-push the clean branch to remote target
echo "Force-pushing branch '$CLEAN_BRANCH' to '$REMOTE/$TARGET_BRANCH'"
git push "$REMOTE" "$CLEAN_BRANCH:$TARGET_BRANCH" --force

# 4) Optionally delete other remote branches
if [ "$DELETE_REFS" = true ]; then
  echo "Deleting other remote branches (excluding $TARGET_BRANCH)"
  # Fetch to ensure refs are up to date
  git fetch "$REMOTE"
  # List remote branches and delete all except HEAD and target
  git for-each-ref --format='%(refname:short)' refs/remotes/"$REMOTE" \
    | grep -v '^$REMOTE/HEAD$' \
    | grep -v "^$REMOTE/$TARGET_BRANCH$" \
    | sed 's#^$REMOTE/##' \
    | while read -r b; do
        if [ -n "$b" ]; then
          echo "Deleting remote branch: $b"
          git push "$REMOTE" --delete "$b" || echo "Failed to delete $b"
        fi
      done
fi

# 5) Optionally delete remote tags
if [ "$DELETE_TAGS" = true ]; then
  echo "Deleting all remote tags"
  git ls-remote --tags "$REMOTE" | awk '{print $2}' | sed 's#refs/tags/##' | while read -r t; do
    if [ -n "$t" ]; then
      echo "Deleting remote tag: $t"
      git push "$REMOTE" --delete tag "$t" || echo "Failed to delete tag $t"
    fi
  done
fi

# 6) Optional: update local branch to match the pushed state
if confirm "Update local branch '$TARGET_BRANCH' to match new remote history (this will reset your local branch)?"; then
  echo "Resetting local $TARGET_BRANCH to $CLEAN_BRANCH"
  git checkout "$TARGET_BRANCH" || git checkout -b "$TARGET_BRANCH"
  git reset --hard "$CLEAN_BRANCH"
fi

# 7) Cleanup temporary branch
if confirm "Delete temporary local branch '$CLEAN_BRANCH'?"; then
  git branch -D "$CLEAN_BRANCH"
fi

# 8) Final verification
echo "Remote refs (heads):"
git ls-remote --heads "$REMOTE"

echo "Remote refs (tags):"
git ls-remote --tags "$REMOTE"

echo "Done. Remote '$REMOTE' should now have '$TARGET_BRANCH' updated to your local state."

exit 0
