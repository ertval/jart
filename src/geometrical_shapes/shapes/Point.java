package geometrical_shapes.shapes;

import geometrical_shapes.interfaces.Drawable;
import geometrical_shapes.interfaces.Displayable;
import java.awt.Color;
import java.util.Random;

/**
 * Represents a point in 2D space.
 * A point is defined by its x and y coordinates.
 */
public class Point implements Drawable {
    private int x;
    private int y;
    private Color color;
    private static Random random = new Random();
    
    /**
     * Create a new Point with specified coordinates.
     * 
     * @param x the x-coordinate
     * @param y the y-coordinate
     */
    public Point(int x, int y) {
        this.x = x;
        this.y = y;
        this.color = generateRandomColor();
    }
    
    /**
     * Create a new Point with specified coordinates and color.
     * 
     * @param x the x-coordinate
     * @param y the y-coordinate
     * @param color the color of the point
     */
    public Point(int x, int y, Color color) {
        this.x = x;
        this.y = y;
        this.color = color;
    }
    
    /**
     * Generate a random point within the specified bounds.
     * 
     * @param maxX maximum x-coordinate (exclusive)
     * @param maxY maximum y-coordinate (exclusive)
     * @return a randomly generated Point
     */
    public static Point random(int maxX, int maxY) {
        int randomX = random.nextInt(Math.max(1, maxX));
        int randomY = random.nextInt(Math.max(1, maxY));
        return new Point(randomX, randomY);
    }
    
    /**
     * Draw this point on the given displayable surface.
     * Points are drawn as small circles for visibility.
     * 
     * @param displayable the surface to draw on
     */
    @Override
    public void draw(Displayable displayable) {
        // Draw a small circle around the point for visibility
        for (int dx = -2; dx <= 2; dx++) {
            for (int dy = -2; dy <= 2; dy++) {
                if (dx * dx + dy * dy <= 4) { // Circle with radius 2
                    displayable.display(x + dx, y + dy, color);
                }
            }
        }
    }
    
    /**
     * Get the color of this point.
     * 
     * @return the color of the point
     */
    @Override
    public Color getColor() {
        return color;
    }
    
    /**
     * Get the x-coordinate of this point.
     * 
     * @return the x-coordinate
     */
    public int getX() {
        return x;
    }
    
    /**
     * Get the y-coordinate of this point.
     * 
     * @return the y-coordinate
     */
    public int getY() {
        return y;
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
        return String.format("Point(%d, %d)", x, y);
    }
    
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Point point = (Point) obj;
        return x == point.x && y == point.y;
    }
    
    @Override
    public int hashCode() {
        return 31 * x + y;
    }
}
