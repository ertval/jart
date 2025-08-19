package geometrical_shapes.shapes;

import geometrical_shapes.interfaces.Drawable;
import geometrical_shapes.interfaces.Displayable;
import java.awt.Color;
import java.util.Random;

/**
 * Represents a line segment between two points.
 */
public class Line implements Drawable {
    private Point start;
    private Point end;
    private Color color;
    private static Random random = new Random();
    
    /**
     * Create a new Line between two points.
     * 
     * @param start the starting point of the line
     * @param end the ending point of the line
     */
    public Line(Point start, Point end) {
        this.start = start;
        this.end = end;
        this.color = generateRandomColor();
    }
    
    /**
     * Create a new Line between two points with specified color.
     * 
     * @param start the starting point of the line
     * @param end the ending point of the line
     * @param color the color of the line
     */
    public Line(Point start, Point end, Color color) {
        this.start = start;
        this.end = end;
        this.color = color;
    }
    
    /**
     * Generate a random line within the specified bounds.
     * 
     * @param maxX maximum x-coordinate (exclusive)
     * @param maxY maximum y-coordinate (exclusive)
     * @return a randomly generated Line
     */
    public static Line random(int maxX, int maxY) {
        Point randomStart = Point.random(maxX, maxY);
        Point randomEnd = Point.random(maxX, maxY);
        return new Line(randomStart, randomEnd);
    }
    
    /**
     * Draw this line on the given displayable surface using Bresenham's line algorithm.
     * 
     * @param displayable the surface to draw on
     */
    @Override
    public void draw(Displayable displayable) {
        int x0 = start.getX();
        int y0 = start.getY();
        int x1 = end.getX();
        int y1 = end.getY();
        
        // Bresenham's line algorithm
        int dx = Math.abs(x1 - x0);
        int dy = Math.abs(y1 - y0);
        int sx = x0 < x1 ? 1 : -1;
        int sy = y0 < y1 ? 1 : -1;
        int err = dx - dy;
        
        int x = x0;
        int y = y0;
        
        while (true) {
            displayable.display(x, y, color);
            
            if (x == x1 && y == y1) {
                break;
            }
            
            int e2 = 2 * err;
            if (e2 > -dy) {
                err -= dy;
                x += sx;
            }
            if (e2 < dx) {
                err += dx;
                y += sy;
            }
        }
    }
    
    /**
     * Get the color of this line.
     * 
     * @return the color of the line
     */
    @Override
    public Color getColor() {
        return color;
    }
    
    /**
     * Get the starting point of this line.
     * 
     * @return the starting point
     */
    public Point getStart() {
        return start;
    }
    
    /**
     * Get the ending point of this line.
     * 
     * @return the ending point
     */
    public Point getEnd() {
        return end;
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
        return String.format("Line[%s -> %s]", start, end);
    }
}
