package geometrical_shapes.shapes;

import geometrical_shapes.interfaces.Drawable;
import geometrical_shapes.interfaces.Displayable;
import java.awt.Color;
import java.util.Random;

/**
 * Represents a rectangle defined by two diagonal points.
 */
public class Rectangle implements Drawable {
    private Point topLeft;
    private Point bottomRight;
    private Color color;
    private static Random random = new Random();
    
    /**
     * Create a new Rectangle with two diagonal points.
     * 
     * @param p1 the first point (will be used to determine bounds)
     * @param p2 the second point (will be used to determine bounds)
     */
    public Rectangle(Point p1, Point p2) {
        // Ensure topLeft is actually top-left and bottomRight is bottom-right
        int minX = Math.min(p1.getX(), p2.getX());
        int minY = Math.min(p1.getY(), p2.getY());
        int maxX = Math.max(p1.getX(), p2.getX());
        int maxY = Math.max(p1.getY(), p2.getY());
        
        this.topLeft = new Point(minX, minY);
        this.bottomRight = new Point(maxX, maxY);
        this.color = generateRandomColor();
    }
    
    /**
     * Create a new Rectangle with two diagonal points and specified color.
     * 
     * @param p1 the first point (will be used to determine bounds)
     * @param p2 the second point (will be used to determine bounds)
     * @param color the color of the rectangle
     */
    public Rectangle(Point p1, Point p2, Color color) {
        // Ensure topLeft is actually top-left and bottomRight is bottom-right
        int minX = Math.min(p1.getX(), p2.getX());
        int minY = Math.min(p1.getY(), p2.getY());
        int maxX = Math.max(p1.getX(), p2.getX());
        int maxY = Math.max(p1.getY(), p2.getY());
        
        this.topLeft = new Point(minX, minY);
        this.bottomRight = new Point(maxX, maxY);
        this.color = color;
    }
    
    /**
     * Generate a random rectangle within the specified bounds.
     * 
     * @param maxX maximum x-coordinate (exclusive)
     * @param maxY maximum y-coordinate (exclusive)
     * @return a randomly generated Rectangle
     */
    public static Rectangle random(int maxX, int maxY) {
        Point randomP1 = Point.random(maxX, maxY);
        Point randomP2 = Point.random(maxX, maxY);
        return new Rectangle(randomP1, randomP2);
    }
    
    /**
     * Draw this rectangle on the given displayable surface.
     * Draws the four edges of the rectangle.
     * 
     * @param displayable the surface to draw on
     */
    @Override
    public void draw(Displayable displayable) {
        Point topRight = new Point(bottomRight.getX(), topLeft.getY());
        Point bottomLeft = new Point(topLeft.getX(), bottomRight.getY());
        
        // Draw the four edges of the rectangle
        Line top = new Line(topLeft, topRight, color);
        Line right = new Line(topRight, bottomRight, color);
        Line bottom = new Line(bottomRight, bottomLeft, color);
        Line left = new Line(bottomLeft, topLeft, color);
        
        top.draw(displayable);
        right.draw(displayable);
        bottom.draw(displayable);
        left.draw(displayable);
    }
    
    /**
     * Get the color of this rectangle.
     * 
     * @return the color of the rectangle
     */
    @Override
    public Color getColor() {
        return color;
    }
    
    /**
     * Get the top-left point of this rectangle.
     * 
     * @return the top-left point
     */
    public Point getTopLeft() {
        return topLeft;
    }
    
    /**
     * Get the bottom-right point of this rectangle.
     * 
     * @return the bottom-right point
     */
    public Point getBottomRight() {
        return bottomRight;
    }
    
    /**
     * Get the width of this rectangle.
     * 
     * @return the width
     */
    public int getWidth() {
        return bottomRight.getX() - topLeft.getX();
    }
    
    /**
     * Get the height of this rectangle.
     * 
     * @return the height
     */
    public int getHeight() {
        return bottomRight.getY() - topLeft.getY();
    }
    
    /**
     * Generate a random color.
     * 
     * @return a randomly generated Color
     */
    private static Color generateRandomColor() {
        return new Color(
            random.nextInt(256),
            random.nextInt(256),
            random.nextInt(256)
        );
    }
    
    @Override
    public String toString() {
        return String.format("Rectangle[%s -> %s]", topLeft, bottomRight);
    }
}
