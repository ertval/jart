# Jart Usage Guide

## Running the Project

### 1. Basic Demo Mode
Generates a standard image with predefined shapes:
```bash
java -cp build Main
# Output: image.png (1000x1000, white background, 50+ shapes)
```

### 2. Interactive Mode
Allows full customization of the artwork:
```bash
java -cp build InteractiveMain
```

#### Interactive Options:
- **Canvas Size**: Custom width/height (100-5000 pixels)
- **Background Colors**:
  - White (default)
  - Black
  - Light Gray  
  - Dark Blue
  - Dark Green
  - Maroon
  - Custom RGB (specify R,G,B values 0-255)
- **Shape Count**: Number of random shapes (0-500)
- **Center Text**: Optional text overlay (max 50 characters)

#### Example Interactive Session:
```
Enter image width (default 1000): 800
Enter image height (default 1000): 600
Choose background color:
1. White (default)
2. Black
3. Light Gray
4. Dark Blue
5. Dark Green
6. Maroon  
7. Custom RGB
Enter choice (1-7): 4
How many random shapes to generate (default 50): 25
Enter text to display in center (press Enter to skip): MY ART

✅ Artwork created successfully: custom_image.png
📐 Dimensions: 800x600 pixels
🎨 Background: RGB(25, 25, 112)
🔢 Random shapes: 25
📝 Center text: "MY ART"
```

### 3. Automated Demo
Quick demonstration with preset values:
```bash
./demo.sh
# Creates custom_image.png with demo settings
```

### 4. Testing
Run comprehensive test suite:
```bash
java -cp build test.JartTest
# Verifies all 42 test cases pass
```

## Features Demonstrated

### Standard Shapes Generated:
- Rectangle (50,50) to (300,200)
- Large triangle covering canvas area
- Diagonal crossing lines
- User-specified count of random shapes

### Random Shape Types:
- Circles (various sizes and positions)
- Rectangles (random orientations)
- Triangles (random vertices)
- Lines (random endpoints)
- Points (small visible dots)
- Pentagons (5-sided polygons)
- Cubes (3D wireframe projection)

### Visual Features:
- Anti-aliased rendering for smooth graphics
- Automatic color generation for visual variety
- Smart text contrast (white on dark, black on light)
- Text shadow for better readability
- Proper bounds checking for all shapes

## Output Files
- `image.png` - Standard demo output
- `custom_image.png` - Interactive mode output
- Generated images are excluded from git via .gitignore
