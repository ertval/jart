#!/bin/bash

# Quick demo script for the interactive version
# This script demonstrates the interactive features with preset inputs

echo "🎨 Jart Interactive Demo - Automated Demo"
echo "========================================="

# Create a demo input file
cat << 'EOF' > demo_input.txt
1000
1000
3
50
JART DEMO
EOF

echo "Creating demo artwork with"

# Run the interactive version with demo inputs
java -cp build InteractiveMain < demo_input.txt

# Clean up
rm -f demo_input.txt

echo ""
echo "✅ Demo complete! Check custom_image.png"
