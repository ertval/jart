#!/bin/bash

# Jart Build Script
# Compiles all Java source files and runs tests

echo "🏗️  Building Jart (Java Art) Project..."

# Clean build directory
rm -rf build
mkdir -p build

echo "📁 Compiling interfaces..."
javac -d build src/geometrical_shapes/interfaces/*.java
if [ $? -ne 0 ]; then echo "❌ Interface compilation failed"; exit 1; fi

echo "📁 Compiling shapes..."
javac -d build -cp build src/geometrical_shapes/shapes/*.java
if [ $? -ne 0 ]; then echo "❌ Shapes compilation failed"; exit 1; fi

echo "📁 Compiling bonus shapes..."
javac -d build -cp build src/geometrical_shapes/bonus/*.java
if [ $? -ne 0 ]; then echo "❌ Bonus shapes compilation failed"; exit 1; fi

echo "📁 Compiling Image class..."
javac -d build -cp build src/geometrical_shapes/*.java
if [ $? -ne 0 ]; then echo "❌ Image class compilation failed"; exit 1; fi

echo "📁 Compiling Main classes..."
javac -d build -cp build Main.java
if [ $? -ne 0 ]; then echo "❌ Main class compilation failed"; exit 1; fi

javac -d build -cp build InteractiveMain.java
if [ $? -ne 0 ]; then echo "❌ InteractiveMain class compilation failed"; exit 1; fi

echo "📁 Compiling tests..."
javac -d build -cp build test/*.java
if [ $? -ne 0 ]; then echo "❌ Test compilation failed"; exit 1; fi

echo "✅ Compilation successful!"

echo ""
echo "🧪 Running tests..."
java -cp build test.JartTest

echo ""
echo "🎨 Running main program..."
java -cp build Main

echo ""
echo "🎉 Build complete! Check image.png for output."
echo ""
echo "To run the interactive version, use:"
echo "    java -cp build InteractiveMain"
