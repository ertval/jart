package geometrical_shapes.shapes;

import geometrical_shapes.interfaces.Drawable;
import geometrical_shapes.interfaces.Displayable;
import java.awt.Color;
import java.util.Random;

/**
 * Represents a triangle defined by three points.
 */
public class Triangle implements Drawable {
    private Point p1;
    private Point p2;
    private Point p3;
    private Color color;
    private static Random random = new Random();
    
    /**
     * Create a new Triangle with three points.
     * 
     * @param p1 the first point
     * @param p2 the second point
     * @param p3 the third point
     */
    public Triangle(Point p1, Point p2, Point p3) {
        this.p1 = p1;
        this.p2 = p2;
        this.p3 = p3;
        this.color = generateRandomColor();
    }
    
    /**
     * Create a new Triangle with three points and specified color.
     * 
     * @param p1 the first point
     * @param p2 the second point
     * @param p3 the third point
     * @param color the color of the triangle
     */
    public Triangle(Point p1, Point p2, Point p3, Color color) {
        this.p1 = p1;
        this.p2 = p2;
        this.p3 = p3;
        this.color = color;
    }
    
    /**
     * Generate a random triangle within the specified bounds.
     * 
     * @param maxX maximum x-coordinate (exclusive)
     * @param maxY maximum y-coordinate (exclusive)
     * @return a randomly generated Triangle
     */
    public static Triangle random(int maxX, int maxY) {
        Point randomP1 = Point.random(maxX, maxY);
        Point randomP2 = Point.random(maxX, maxY);
        Point randomP3 = Point.random(maxX, maxY);
        return new Triangle(randomP1, randomP2, randomP3);
    }
    
    /**
     * Draw this triangle on the given displayable surface.
     * Draws the three edges of the triangle.
     * 
     * @param displayable the surface to draw on
     */
    @Override
    public void draw(Displayable displayable) {
        // Draw the three edges of the triangle
        Line edge1 = new Line(p1, p2, color);
        Line edge2 = new Line(p2, p3, color);
        Line edge3 = new Line(p3, p1, color);
        
        edge1.draw(displayable);
        edge2.draw(displayable);
        edge3.draw(displayable);
    }
    
    /**
     * Get the color of this triangle.
     * 
     * @return the color of the triangle
     */
    @Override
    public Color getColor() {
        return color;
    }
    
    /**
     * Get the first point of this triangle.
     * 
     * @return the first point
     */
    public Point getP1() {
        return p1;
    }
    
    /**
     * Get the second point of this triangle.
     * 
     * @return the second point
     */
    public Point getP2() {
        return p2;
    }
    
    /**
     * Get the third point of this triangle.
     * 
     * @return the third point
     */
    public Point getP3() {
        return p3;
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
        return String.format("Triangle[%s, %s, %s]", p1, p2, p3);
    }
}
