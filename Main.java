import geometrical_shapes.Image;
import geometrical_shapes.shapes.*;

/**
 * Main class to demonstrate the Jart (Java Art) geometric shapes rendering system.
 * This class creates various shapes and renders them to a PNG image.
 */
public class Main {
    public static void main(String[] args) {
        // Create a 1000x1000 image
        Image image = new Image(1000, 1000);
        
        // Create and draw a rectangle
        Rectangle rectangle = new Rectangle(new Point(50, 50), new Point(300, 200));
        rectangle.draw(image);
        
        // Create and draw a triangle
        Triangle triangle = new Triangle(new Point(100, 100), new Point(900, 900), new Point(100, 900));
        triangle.draw(image);
        
        // Create and draw some lines
        Line line1 = new Line(new Point(0, 0), new Point(1000, 1000));
        line1.draw(image);
        
        Line line2 = new Line(new Point(1000, 0), new Point(0, 1000));
        line2.draw(image);
        
        // Create and draw 50 random circles
        for (int i = 0; i < 50; i++) {
            Circle circle = Circle.random(image.getWidth(), image.getHeight());
            circle.draw(image);
        }
        
        // Add some random shapes for variety
        for (int i = 0; i < 10; i++) {
            Rectangle randomRect = Rectangle.random(image.getWidth(), image.getHeight());
            randomRect.draw(image);
            
            Triangle randomTriangle = Triangle.random(image.getWidth(), image.getHeight());
            randomTriangle.draw(image);
            
            Line randomLine = Line.random(image.getWidth(), image.getHeight());
            randomLine.draw(image);
        }
        
        // Save the image
        image.save("image.png");
        
        // Clean up resources
        image.dispose();
        
        System.out.println("Image created successfully: image.png");
        System.out.println("Image dimensions: " + image.getWidth() + "x" + image.getHeight());
    }
}
