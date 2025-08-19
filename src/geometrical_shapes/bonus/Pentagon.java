package geometrical_shapes.bonus;

import geometrical_shapes.interfaces.Drawable;
import geometrical_shapes.interfaces.Displayable;
import geometrical_shapes.shapes.Point;
import geometrical_shapes.shapes.Line;
import java.awt.Color;
import java.util.Random;

/**
 * Represents a regular pentagon (5-sided polygon).
 * The pentagon is defined by its center point and radius.
 */
public class Pentagon implements Drawable {
    private Point center;
    private int radius;
    private Color color;
    private static Random random = new Random();
    
    /**
     * Create a new Pentagon with center point and radius.
     * 
     * @param center the center point of the pentagon
     * @param radius the radius (distance from center to vertices)
     */
    public Pentagon(Point center, int radius) {
        this.center = center;
        this.radius = Math.max(10, radius); // Ensure minimum size
        this.color = generateRandomColor();
    }
    
    /**
     * Create a new Pentagon with center point, radius, and specified color.
     * 
     * @param center the center point of the pentagon
     * @param radius the radius (distance from center to vertices)
     * @param color the color of the pentagon
     */
    public Pentagon(Point center, int radius, Color color) {
        this.center = center;
        this.radius = Math.max(10, radius); // Ensure minimum size
        this.color = color;
    }
    
    /**
     * Generate a random pentagon within the specified bounds.
     * 
     * @param maxX maximum x-coordinate (exclusive)
     * @param maxY maximum y-coordinate (exclusive)
     * @return a randomly generated Pentagon
     */
    public static Pentagon random(int maxX, int maxY) {
        int margin = Math.min(maxX, maxY) / 8;
        Point randomCenter = new Point(
            margin + random.nextInt(Math.max(1, maxX - 2 * margin)),
            margin + random.nextInt(Math.max(1, maxY - 2 * margin))
        );
        
        int maxRadius = Math.max(20, margin / 2);
        int randomRadius = 20 + random.nextInt(Math.max(1, maxRadius - 19));
        
        return new Pentagon(randomCenter, randomRadius);
    }
    
    /**
     * Draw this pentagon on the given displayable surface.
     * Uses trigonometry to calculate the 5 vertices and draws connecting lines.
     * 
     * @param displayable the surface to draw on
     */
    @Override
    public void draw(Displayable displayable) {
        Point[] vertices = calculateVertices();
        
        // Draw the 5 edges of the pentagon
        for (int i = 0; i < 5; i++) {
            int nextIndex = (i + 1) % 5;
            Line edge = new Line(vertices[i], vertices[nextIndex], color);
            edge.draw(displayable);
        }
    }
    
    /**
     * Calculate the 5 vertices of the pentagon using trigonometry.
     * 
     * @return array of 5 Points representing the vertices
     */
    private Point[] calculateVertices() {
        Point[] vertices = new Point[5];
        double angleStep = 2 * Math.PI / 5; // 72 degrees in radians
        double startAngle = -Math.PI / 2; // Start from top
        
        for (int i = 0; i < 5; i++) {
            double angle = startAngle + i * angleStep;
            int x = center.getX() + (int) (radius * Math.cos(angle));
            int y = center.getY() + (int) (radius * Math.sin(angle));
            vertices[i] = new Point(x, y);
        }
        
        return vertices;
    }
    
    /**
     * Get the color of this pentagon.
     * 
     * @return the color of the pentagon
     */
    @Override
    public Color getColor() {
        return color;
    }
    
    /**
     * Get the center point of this pentagon.
     * 
     * @return the center point
     */
    public Point getCenter() {
        return center;
    }
    
    /**
     * Get the radius of this pentagon.
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
        return String.format("Pentagon[center=%s, radius=%d]", center, radius);
    }
}
