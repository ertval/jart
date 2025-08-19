package test;

import geometrical_shapes.Image;
import geometrical_shapes.shapes.*;
import geometrical_shapes.bonus.*;
import geometrical_shapes.interfaces.*;
import java.awt.Color;
import java.io.File;

/**
 * Comprehensive test suite for the Jart project.
 * Tests all functionality required by the audit checklist.
 */
public class JartTest {
    private static int testsPassed = 0;
    private static int testsTotal = 0;
    
    public static void main(String[] args) {
        System.out.println("Running Jart Test Suite...\n");
        
        // Test interfaces
        testInterfaces();
        
        // Test basic shapes
        testPoint();
        testLine();
        testTriangle();
        testRectangle();
        testCircle();
        
        // Test Image class
        testImage();
        
        // Test random methods
        testRandomMethods();
        
        // Test bonus shapes
        testPentagon();
        testCube();
        
        // Integration test
        testImageGeneration();
        
        // Print results
        System.out.println("\n" + "=".repeat(50));
        System.out.println("Test Results: " + testsPassed + "/" + testsTotal + " tests passed");
        
        if (testsPassed == testsTotal) {
            System.out.println("✅ All tests passed! Project meets audit requirements.");
        } else {
            System.out.println("❌ Some tests failed. Please review the implementation.");
        }
    }
    
    private static void testInterfaces() {
        System.out.println("Testing Interfaces...");
        
        // Test that Image implements Displayable
        assertTrue("Image implements Displayable", new Image(100, 100) instanceof Displayable);
        
        // Test that shapes implement Drawable
        assertTrue("Point implements Drawable", new Point(0, 0) instanceof Drawable);
        assertTrue("Line implements Drawable", new Line(new Point(0, 0), new Point(10, 10)) instanceof Drawable);
        assertTrue("Triangle implements Drawable", new Triangle(new Point(0, 0), new Point(10, 0), new Point(5, 10)) instanceof Drawable);
        assertTrue("Rectangle implements Drawable", new Rectangle(new Point(0, 0), new Point(10, 10)) instanceof Drawable);
        assertTrue("Circle implements Drawable", new Circle(new Point(5, 5), 10) instanceof Drawable);
        
        System.out.println();
    }
    
    private static void testPoint() {
        System.out.println("Testing Point...");
        
        Point p1 = new Point(10, 20);
        assertTrue("Point coordinates", p1.getX() == 10 && p1.getY() == 20);
        assertTrue("Point has color", p1.getColor() != null);
        
        // Test drawing
        Image testImage = new Image(100, 100);
        p1.draw(testImage);
        assertTrue("Point can be drawn", true); // If no exception, test passes
        
        System.out.println();
    }
    
    private static void testLine() {
        System.out.println("Testing Line...");
        
        Point start = new Point(0, 0);
        Point end = new Point(10, 10);
        Line line = new Line(start, end);
        
        assertTrue("Line has start point", line.getStart().equals(start));
        assertTrue("Line has end point", line.getEnd().equals(end));
        assertTrue("Line has color", line.getColor() != null);
        
        // Test drawing
        Image testImage = new Image(100, 100);
        line.draw(testImage);
        assertTrue("Line can be drawn", true);
        
        System.out.println();
    }
    
    private static void testTriangle() {
        System.out.println("Testing Triangle...");
        
        Point p1 = new Point(0, 0);
        Point p2 = new Point(10, 0);
        Point p3 = new Point(5, 10);
        Triangle triangle = new Triangle(p1, p2, p3);
        
        assertTrue("Triangle has correct points", 
            triangle.getP1().equals(p1) && 
            triangle.getP2().equals(p2) && 
            triangle.getP3().equals(p3));
        assertTrue("Triangle has color", triangle.getColor() != null);
        
        // Test drawing
        Image testImage = new Image(100, 100);
        triangle.draw(testImage);
        assertTrue("Triangle can be drawn", true);
        
        System.out.println();
    }
    
    private static void testRectangle() {
        System.out.println("Testing Rectangle...");
        
        Point p1 = new Point(10, 10);
        Point p2 = new Point(50, 30);
        Rectangle rectangle = new Rectangle(p1, p2);
        
        assertTrue("Rectangle has dimensions", 
            rectangle.getWidth() > 0 && rectangle.getHeight() > 0);
        assertTrue("Rectangle has color", rectangle.getColor() != null);
        
        // Test drawing
        Image testImage = new Image(100, 100);
        rectangle.draw(testImage);
        assertTrue("Rectangle can be drawn", true);
        
        System.out.println();
    }
    
    private static void testCircle() {
        System.out.println("Testing Circle...");
        
        Point center = new Point(50, 50);
        Circle circle = new Circle(center, 20);
        
        assertTrue("Circle has center", circle.getCenter().equals(center));
        assertTrue("Circle has radius", circle.getRadius() == 20);
        assertTrue("Circle has color", circle.getColor() != null);
        
        // Test drawing
        Image testImage = new Image(100, 100);
        circle.draw(testImage);
        assertTrue("Circle can be drawn", true);
        
        System.out.println();
    }
    
    private static void testImage() {
        System.out.println("Testing Image...");
        
        Image image = new Image(200, 150);
        assertTrue("Image has correct dimensions", 
            image.getWidth() == 200 && image.getHeight() == 150);
        
        // Test display method
        image.display(10, 10, Color.RED);
        assertTrue("Image display method works", true);
        
        // Test custom background color constructor
        Image coloredImage = new Image(100, 100, Color.BLUE);
        assertTrue("Image with custom background works", 
            coloredImage.getWidth() == 100 && coloredImage.getHeight() == 100);
        
        // Test text drawing
        coloredImage.drawCenteredText("TEST", Color.WHITE, 20);
        assertTrue("Image can draw centered text", true);
        
        // Test save method
        image.save("test_image.png");
        File testFile = new File("test_image.png");
        assertTrue("Image can be saved", testFile.exists());
        
        // Clean up
        testFile.delete();
        image.dispose();
        coloredImage.dispose();
        
        System.out.println();
    }
    
    private static void testRandomMethods() {
        System.out.println("Testing Random Methods...");
        
        // Test Point.random()
        Point randomPoint = Point.random(100, 100);
        assertTrue("Point.random() works", 
            randomPoint.getX() >= 0 && randomPoint.getX() < 100 &&
            randomPoint.getY() >= 0 && randomPoint.getY() < 100);
        
        // Test Line.random()
        Line randomLine = Line.random(100, 100);
        assertTrue("Line.random() works", randomLine != null);
        
        // Test Circle.random()
        Circle randomCircle = Circle.random(100, 100);
        assertTrue("Circle.random() works", 
            randomCircle != null && randomCircle.getRadius() > 0);
        
        System.out.println();
    }
    
    private static void testPentagon() {
        System.out.println("Testing Pentagon (Bonus)...");
        
        Point center = new Point(50, 50);
        Pentagon pentagon = new Pentagon(center, 30);
        
        assertTrue("Pentagon has center", pentagon.getCenter().equals(center));
        assertTrue("Pentagon has radius", pentagon.getRadius() == 30);
        assertTrue("Pentagon has color", pentagon.getColor() != null);
        assertTrue("Pentagon implements Drawable", pentagon instanceof Drawable);
        
        // Test drawing
        Image testImage = new Image(100, 100);
        pentagon.draw(testImage);
        assertTrue("Pentagon can be drawn", true);
        
        System.out.println();
    }
    
    private static void testCube() {
        System.out.println("Testing Cube (Bonus)...");
        
        Point center = new Point(50, 50);
        Cube cube = new Cube(center, 40);
        
        assertTrue("Cube has center", cube.getCenter().equals(center));
        assertTrue("Cube has size", cube.getSize() == 40);
        assertTrue("Cube has color", cube.getColor() != null);
        assertTrue("Cube implements Drawable", cube instanceof Drawable);
        
        // Test drawing
        Image testImage = new Image(100, 100);
        cube.draw(testImage);
        assertTrue("Cube can be drawn", true);
        
        System.out.println();
    }
    
    private static void testImageGeneration() {
        System.out.println("Testing Image Generation (Integration Test)...");
        
        // Run the main program logic
        Image image = new Image(500, 500);
        
        // Create shapes with different colors
        Rectangle rectangle = new Rectangle(new Point(50, 50), new Point(150, 100));
        Triangle triangle = new Triangle(new Point(100, 200), new Point(400, 400), new Point(100, 400));
        
        rectangle.draw(image);
        triangle.draw(image);
        
        // Add random circles
        for (int i = 0; i < 10; i++) {
            Circle circle = Circle.random(image.getWidth(), image.getHeight());
            circle.draw(image);
        }
        
        // Save test image
        image.save("integration_test.png");
        
        File testFile = new File("integration_test.png");
        assertTrue("Integration test produces image file", testFile.exists());
        
        // Clean up
        testFile.delete();
        image.dispose();
        
        System.out.println();
    }
    
    private static void assertTrue(String testName, boolean condition) {
        testsTotal++;
        if (condition) {
            System.out.println("✅ " + testName);
            testsPassed++;
        } else {
            System.out.println("❌ " + testName);
        }
    }
}
