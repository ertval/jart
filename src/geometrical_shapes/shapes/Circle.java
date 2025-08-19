package geometrical_shapes.shapes;

import geometrical_shapes.interfaces.Drawable;
import geometrical_shapes.interfaces.Displayable;
import java.awt.Color;
import java.util.Random;

/**
 * Represents a circle defined by a center point and radius.
 */
public class Circle implements Drawable {
    private Point center;
    private int radius;
    private Color color;
    private static Random random = new Random();
    
    /**
     * Create a new Circle with center point and radius.
     * 
     * @param center the center point of the circle
     * @param radius the radius of the circle
     */
    public Circle(Point center, int radius) {
        this.center = center;
        this.radius = Math.max(1, radius); // Ensure radius is at least 1
        this.color = generateRandomColor();
    }
    
    /**
     * Create a new Circle with center point, radius, and specified color.
     * 
     * @param center the center point of the circle
     * @param radius the radius of the circle
     * @param color the color of the circle
     */
    public Circle(Point center, int radius, Color color) {
        this.center = center;
        this.radius = Math.max(1, radius); // Ensure radius is at least 1
        this.color = color;
    }
    
    /**
     * Generate a random circle within the specified bounds.
     * 
     * @param maxX maximum x-coordinate (exclusive)
     * @param maxY maximum y-coordinate (exclusive)
     * @return a randomly generated Circle
     */
    public static Circle random(int maxX, int maxY) {
        // Generate center point with some margin for the radius
        int margin = Math.min(maxX, maxY) / 10; // 10% margin
        Point randomCenter = new Point(
            margin + random.nextInt(Math.max(1, maxX - 2 * margin)),
            margin + random.nextInt(Math.max(1, maxY - 2 * margin))
        );
        
        // Generate random radius (between 10 and margin)
        int maxRadius = Math.max(10, margin);
        int randomRadius = 10 + random.nextInt(maxRadius - 9);
        
        return new Circle(randomCenter, randomRadius);
    }
    
    /**
     * Draw this circle on the given displayable surface using Bresenham's circle algorithm.
     * 
     * @param displayable the surface to draw on
     */
    @Override
    public void draw(Displayable displayable) {
        int cx = center.getX();
        int cy = center.getY();
        
        // Bresenham's circle algorithm
        int x = 0;
        int y = radius;
        int d = 3 - 2 * radius;
        
        drawCirclePoints(displayable, cx, cy, x, y);
        
        while (y >= x) {
            x++;
            
            if (d > 0) {
                y--;
                d = d + 4 * (x - y) + 10;
            } else {
                d = d + 4 * x + 6;
            }
            
            drawCirclePoints(displayable, cx, cy, x, y);
        }
    }
    
    /**
     * Helper method to draw the 8 symmetric points of a circle.
     * 
     * @param displayable the surface to draw on
     * @param cx center x-coordinate
     * @param cy center y-coordinate
     * @param x current x offset
     * @param y current y offset
     */
    private void drawCirclePoints(Displayable displayable, int cx, int cy, int x, int y) {
        displayable.display(cx + x, cy + y, color);
        displayable.display(cx - x, cy + y, color);
        displayable.display(cx + x, cy - y, color);
        displayable.display(cx - x, cy - y, color);
        displayable.display(cx + y, cy + x, color);
        displayable.display(cx - y, cy + x, color);
        displayable.display(cx + y, cy - x, color);
        displayable.display(cx - y, cy - x, color);
    }
    
    /**
     * Get the color of this circle.
     * 
     * @return the color of the circle
     */
    @Override
    public Color getColor() {
        return color;
    }
    
    /**
     * Get the center point of this circle.
     * 
     * @return the center point
     */
    public Point getCenter() {
        return center;
    }
    
    /**
     * Get the radius of this circle.
     * 
     * @return the radius
     */
    public int getRadius() {
        return radius;
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
        return String.format("Circle[center=%s, radius=%d]", center, radius);
    }
}
