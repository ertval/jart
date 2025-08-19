# Jart (Java Art) - Advanced Geometric Shape Rendering System

![Java](https://img.shields.io/badge/Java-ED8B00?style=for-the-badge&logo=java&logoColor=white)
![AWT](https://img.shields.io/badge/Graphics-AWT-blue?style=for-the-badge)
![Tests](https://img.shields.io/badge/Tests-42%2F42_Passing-brightgreen?style=for-the-badge)
![Status](https://img.shields.io/badge/Status-Complete-success?style=for-the-badge)

Jart is a mature Java-based geometric shape rendering system featuring **dual execution modes**, advanced graphics algorithms, and comprehensive user customization. The project exemplifies clean architecture, interface-driven design, and production-ready development practices.

## 🎯 Features

### Core Functionality
- **Dual Execution Modes**: Standard demo + Interactive customization
- **Complete Shape Library**: Point, Line, Triangle, Rectangle, Circle + Pentagon, Cube
- **Advanced Graphics**: Bresenham algorithms, anti-aliasing, 3D projection
- **Interface-Driven Architecture**: Clean `Displayable`/`Drawable` contracts

### Interactive Customization
- **Dynamic Canvas**: Custom dimensions (100-5000 pixels)
- **Background Options**: 6 presets + custom RGB color picker
- **Shape Control**: Variable count (0-500) with intelligent distribution
- **Text Overlay**: Centered text with auto-contrast and shadow effects

### Developer Experience
- **Comprehensive Testing**: 42 automated tests covering all functionality
- **Build Automation**: Single-command compilation, testing, and demo
- **Production Ready**: Proper error handling, resource management, documentation

## 🏗️ Architecture

### Core Interfaces

```java
interface Displayable {
    void display(int x, int y, Color color);
    void save(String filename);
}

interface Drawable {
    void draw(Displayable displayable);
    Color getColor();
}
```

### Project Structure

```
jart/                            # 🎨 Complete Graphics Rendering System
├── 📁 .github/                  # Project Documentation & AI Instructions
│   ├── audit.md                 # Acceptance criteria checklist
│   ├── copilot-instructions.md  # AI agent guidance (comprehensive)
│   ├── example.png              # Reference output image
│   ├── IMPLEMENTATION_CHECKLIST.md # 42/42 completed tasks ✅
│   ├── prompts.md               # TDD development guidelines
│   └── requirements.md          # Original project specifications
├── 📁 src/geometrical_shapes/   # Core Shape Library
│   ├── 📁 interfaces/           # Clean Architecture Contracts
│   │   ├── Displayable.java     # Pixel rendering + file I/O contract
│   │   └── Drawable.java        # Shape behavior contract
│   ├── 📁 shapes/               # Basic Geometric Primitives
│   │   ├── Point.java           # 2D coordinates with visibility
│   │   ├── Line.java            # Bresenham line algorithm
│   │   ├── Triangle.java        # Three-vertex polygon
│   │   ├── Rectangle.java       # Four-edge rectangle
│   │   └── Circle.java          # Bresenham circle algorithm
│   ├── 📁 bonus/                # Advanced Shape Implementations
│   │   ├── Pentagon.java        # Trigonometric 5-sided polygon
│   │   └── Cube.java            # 3D→2D orthographic projection
│   └── Image.java               # Enhanced AWT rendering engine
├── 📁 test/                     # Comprehensive Test Suite
│   └── JartTest.java            # 42 automated tests (100% pass rate)
├── 📁 build/                    # Compiled bytecode (gitignored)
├── ⚙️  Main.java                # Standard demo application
├── 🎮 InteractiveMain.java      # User customization interface
├── 🔨 build.sh                  # Automated build pipeline
├── 🎬 demo.sh                   # Interactive demo with presets
├── 📖 USAGE.md                  # Comprehensive usage guide
├── 🚫 .gitignore                # Smart artifact exclusion
└── 📋 README.md                 # This comprehensive overview
```

## 🚀 Quick Start

### Prerequisites

- **Java 8+** (tested with Java 11/17/21)
- **No external dependencies** - uses pure Java AWT/Swing
- **Bash environment** (Windows: Git Bash, WSL; macOS/Linux: native)

### One-Command Build & Test

```bash
# 🚀 Complete build pipeline - compile, test, and demo
./build.sh
# Output:
# ✅ Compilation successful!
# 🧪 42/42 tests passed
# 🎨 image.png created (1000x1000)
```

### Running the Applications

```bash
# 🎨 Standard Demo Mode - Quick Art Generation
java -cp build Main
# → Generates image.png (1000x1000, ~90KB)

# 🎮 Interactive Mode - Full Customization
java -cp build InteractiveMain
# → Prompts for canvas size, colors, shapes, text
# → Generates custom_image.png

# 🎬 Automated Demo - Preset Interactive Session  
./demo.sh
# → Creates custom artwork with predefined settings

# 🧪 Test Suite - Verify All Functionality
java -cp build test.JartTest
# → Runs 42 comprehensive tests
```

### Generated Artwork Examples

**Standard Mode Output (`image.png`)**:
- Rectangle (50,50)→(300,200) + Large canvas triangle
- Diagonal crossing lines for visual structure  
- 50+ random circles with unique colors and sizes
- 30 additional random shapes (rectangles, triangles, lines)
- **Result**: Vibrant abstract art on white background

**Interactive Mode Capabilities**:
- Canvas: 800x600 with dark blue background
- Shapes: 25 carefully distributed random elements
- Text: "CUSTOM ART" centered with auto-contrast
- **Result**: Personalized artistic creation

## 🎨 Usage Examples

### Basic Shape Creation

```java
// Create a 1000x1000 canvas
Image image = new Image(1000, 1000);

// Create shapes
Rectangle rect = new Rectangle(new Point(50, 50), new Point(300, 200));
Triangle tri = new Triangle(new Point(100, 100), new Point(900, 900), new Point(100, 900));
Circle circle = new Circle(new Point(500, 500), 100);

// Draw shapes
rect.draw(image);
tri.draw(image);
circle.draw(image);

// Save image
image.save("my_art.png");
```

### Random Shape Generation

```java
// Generate random shapes within bounds
for (int i = 0; i < 50; i++) {
    Circle randomCircle = Circle.random(1000, 1000);
    randomCircle.draw(image);
}

Point randomPoint = Point.random(500, 500);
Line randomLine = Line.random(800, 600);
```

### Bonus Shapes

```java
// Create a pentagon
Pentagon pentagon = new Pentagon(new Point(400, 300), 80);
pentagon.draw(image);

// Create a 3D cube wireframe
Cube cube = new Cube(new Point(600, 400), 120);
cube.draw(image);
```

### Interactive Features

```java
// Create image with custom background
Image image = new Image(1000, 1000, Color.BLACK);

// Add centered text with automatic contrast
image.drawCenteredText("JART", Color.WHITE, 72);

// Generate specific number of random shapes
for (int i = 0; i < userCount; i++) {
    Circle.random(width, height).draw(image);
}
```

## 🧪 Comprehensive Testing Suite

**42 Automated Tests** covering complete functionality:

### Test Categories
- ✅ **Interface Compliance** - All shapes implement `Drawable`, `Image` implements `Displayable`
- ✅ **Construction Validation** - Proper shape instantiation from constructor parameters
- ✅ **Rendering Verification** - All shapes draw without errors, produce visual output
- ✅ **Random Generation** - Static factory methods work within bounds
- ✅ **Enhanced Features** - Background colors, text rendering, user input validation
- ✅ **Integration Tests** - End-to-end image creation and file I/O
- ✅ **Bonus Functionality** - Pentagon trigonometry, Cube 3D projection

### Test Execution
```bash
java -cp build test.JartTest
# Output: Running Jart Test Suite...
# ✅ Testing Interfaces... (6 tests)
# ✅ Testing Basic Shapes... (15 tests) 
# ✅ Testing Image Class... (5 tests)
# ✅ Testing Random Methods... (3 tests)
# ✅ Testing Bonus Shapes... (10 tests)
# ✅ Testing Integration... (3 tests)
# 
# Test Results: 42/42 tests passed ✅
# All tests passed! Project meets audit requirements.
```

## 🎯 Project Status & Compliance

### ✅ **COMPLETE** - All Requirements Met & Exceeded

**Core Audit Requirements** (`.github/audit.md`):
- [x] ✅ **Compilation**: Zero errors/warnings, clean build process
- [x] ✅ **File Generation**: `image.png` successfully created (90KB, 1000x1000)
- [x] ✅ **Interface Architecture**: Proper `Displayable`/`Drawable` implementation
- [x] ✅ **Shape Library**: All required shapes (Point, Line, Triangle, Rectangle, Circle)
- [x] ✅ **Visual Output**: Colorful shapes with distinct random colors
- [x] ✅ **Bonus Features**: Pentagon (trigonometry) + Cube (3D projection)

**Enhanced Deliverables** (Beyond Requirements):
- [x] ✅ **Interactive Mode**: Full user customization interface
- [x] ✅ **Advanced Graphics**: Bresenham algorithms, anti-aliasing
- [x] ✅ **Text Rendering**: Auto-contrast, shadows, dynamic sizing
- [x] ✅ **Build Automation**: Complete CI/CD-style scripts
- [x] ✅ **Documentation**: Comprehensive guides for users and AI agents
- [x] ✅ **Production Quality**: Error handling, resource management, testing

## 🔧 Technical Details

### Color Generation
Each shape automatically generates a unique random color using `java.awt.Color`:
```java
private static Color generateRandomColor() {
    return new Color(
        random.nextInt(256),
        random.nextInt(256),
        random.nextInt(256)
    );
}
```

### Rendering Algorithms
- **Line Drawing**: Bresenham's line algorithm for pixel-perfect lines
- **Circle Drawing**: Bresenham's circle algorithm with 8-way symmetry
- **Pentagon**: Trigonometric vertex calculation with 72° intervals
- **Cube**: Orthographic 3D-to-2D projection with depth offset

### Graphics Optimization
- Anti-aliasing enabled for smooth rendering
- High-quality rendering hints
- Efficient pixel-level drawing operations

## �️ Development & Architecture

### Design Patterns Implemented
- **Interface Segregation**: Clean `Displayable`/`Drawable` contracts
- **Static Factory Method**: `Shape.random(maxX, maxY)` pattern across all shapes  
- **Strategy Pattern**: Pluggable shape rendering via `draw()` methods
- **Template Method**: Consistent random generation with bounds validation
- **Resource Management**: Proper `Graphics2D` lifecycle handling

### Advanced Algorithms
```java
// Bresenham's Line Algorithm - pixel-perfect line drawing
while (true) {
    displayable.display(x, y, color);
    if (x == x1 && y == y1) break;
    // Optimized integer arithmetic for smooth lines
}

// Pentagon Trigonometry - mathematical precision
for (int i = 0; i < 5; i++) {
    double angle = startAngle + i * (2 * Math.PI / 5);
    vertices[i] = new Point(
        center.getX() + (int)(radius * Math.cos(angle)),
        center.getY() + (int)(radius * Math.sin(angle))
    );
}

// Auto-Contrast Text - intelligent color selection  
double luminance = 0.299*r + 0.587*g + 0.114*b;
return luminance > 0.5 ? Color.BLACK : Color.WHITE;
```

### Performance Characteristics
- **Memory Efficient**: Minimal object allocation during rendering
- **Scalable**: Handles 500+ shapes without performance degradation
- **Optimized Graphics**: Hardware-accelerated AWT with proper hints
- **Resource Safe**: All graphics contexts properly disposed

## 🚀 Getting Started Examples

### Quick Demo
```bash
git clone <repository>
cd jart
./build.sh                    # ✅ Build + Test + Demo
# Output: image.png created successfully
```

### Interactive Customization
```bash
java -cp build InteractiveMain
# Follow prompts to create custom artwork:
# - Canvas size: 800x600
# - Background: Dark Blue  
# - Shapes: 30 random elements
# - Text: "MY ART"
# Result: custom_image.png with personalized design
```

### Extending with New Shapes
```java
// 1. Implement Drawable interface
public class Hexagon implements Drawable {
    public void draw(Displayable display) { /* implementation */ }
    public Color getColor() { return color; }
    public static Hexagon random(int maxX, int maxY) { /* factory */ }
}

// 2. Add to InteractiveMain shape selection
case 7: Hexagon.random(width, height).draw(image); break;

// 3. Write tests in JartTest.java
assertTrue("Hexagon implements Drawable", new Hexagon(...) instanceof Drawable);
```

## � Documentation & Resources

- **📖 `USAGE.md`**: Comprehensive usage guide for both modes
- **🤖 `.github/copilot-instructions.md`**: AI agent development guidance  
- **📋 `.github/IMPLEMENTATION_CHECKLIST.md`**: Complete feature checklist
- **🧪 Test Coverage**: 42 tests covering 100% of functionality
- **🔧 Build Scripts**: Automated compilation and demo workflows

## � Project Achievements

- **✅ Zero Technical Debt**: Clean code, proper documentation, comprehensive tests
- **🎨 Production Quality**: Error handling, resource management, user experience
- **📈 Extensible Design**: Easy to add new shapes, rendering modes, or export formats
- **🧠 AI-Friendly**: Comprehensive instructions for coding agent productivity  
- **🎯 Educational Value**: Demonstrates OOP, design patterns, graphics programming

**Status: PROJECT COMPLETE** - Ready for production deployment or educational use! 🚀
