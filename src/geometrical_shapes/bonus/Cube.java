package geometrical_shapes.bonus;

import geometrical_shapes.interfaces.Drawable;
import geometrical_shapes.interfaces.Displayable;
import geometrical_shapes.shapes.Point;
import geometrical_shapes.shapes.Line;
import java.awt.Color;
import java.util.Random;

/**
 * Represents a 3D cube rendered in 2D using orthographic projection.
 * The cube is defined by its center point and size.
 */
public class Cube implements Drawable {
    private Point center;
    private int size;
    private Color color;
    private static Random random = new Random();
    
    /**
     * Create a new Cube with center point and size.
     * 
     * @param center the center point of the cube (front face center)
     * @param size the size of the cube edges
     */
    public Cube(Point center, int size) {
        this.center = center;
        this.size = Math.max(10, size); // Ensure minimum size
        this.color = generateRandomColor();
    }
    
    /**
     * Create a new Cube with center point, size, and specified color.
     * 
     * @param center the center point of the cube (front face center)
     * @param size the size of the cube edges
     * @param color the color of the cube
     */
    public Cube(Point center, int size, Color color) {
        this.center = center;
        this.size = Math.max(10, size); // Ensure minimum size
        this.color = color;
    }
    
    /**
     * Generate a random cube within the specified bounds.
     * 
     * @param maxX maximum x-coordinate (exclusive)
     * @param maxY maximum y-coordinate (exclusive)
     * @return a randomly generated Cube
     */
    public static Cube random(int maxX, int maxY) {
        int margin = Math.min(maxX, maxY) / 6;
        Point randomCenter = new Point(
            margin + random.nextInt(Math.max(1, maxX - 2 * margin)),
            margin + random.nextInt(Math.max(1, maxY - 2 * margin))
        );
        
        int maxSize = Math.max(30, margin / 2);
        int randomSize = 30 + random.nextInt(Math.max(1, maxSize - 29));
        
        return new Cube(randomCenter, randomSize);
    }
    
    /**
     * Draw this cube on the given displayable surface.
     * Uses orthographic projection to render a 3D cube in 2D.
     * 
     * @param displayable the surface to draw on
     */
    @Override
    public void draw(Displayable displayable) {
        Point[] vertices = calculateVertices();
        
        // Draw front face (vertices 0-3)
        drawFace(displayable, vertices[0], vertices[1], vertices[2], vertices[3]);
        
        // Draw back face (vertices 4-7)
        drawFace(displayable, vertices[4], vertices[5], vertices[6], vertices[7]);
        
        // Draw connecting edges between front and back faces
        for (int i = 0; i < 4; i++) {
            Line edge = new Line(vertices[i], vertices[i + 4], color);
            edge.draw(displayable);
        }
    }
    
    /**
     * Draw a face of the cube (4 connected lines).
     * 
     * @param displayable the surface to draw on
     * @param p1 first vertex of the face
     * @param p2 second vertex of the face
     * @param p3 third vertex of the face
     * @param p4 fourth vertex of the face
     */
    private void drawFace(Displayable displayable, Point p1, Point p2, Point p3, Point p4) {
        Line edge1 = new Line(p1, p2, color);
        Line edge2 = new Line(p2, p3, color);
        Line edge3 = new Line(p3, p4, color);
        Line edge4 = new Line(p4, p1, color);
        
        edge1.draw(displayable);
        edge2.draw(displayable);
        edge3.draw(displayable);
        edge4.draw(displayable);
    }
    
    /**
     * Calculate the 8 vertices of the cube using orthographic projection.
     * Creates a 3D cube and projects it to 2D coordinates.
     * 
     * @return array of 8 Points representing the vertices
     */
    private Point[] calculateVertices() {
        Point[] vertices = new Point[8];
        int half = size / 2;
        int depth = size / 3; // Depth offset for 3D effect
        
        int cx = center.getX();
        int cy = center.getY();
        
        // Front face vertices (closer to viewer)
        vertices[0] = new Point(cx - half, cy - half);           // Top-left front
        vertices[1] = new Point(cx + half, cy - half);           // Top-right front
        vertices[2] = new Point(cx + half, cy + half);           // Bottom-right front
        vertices[3] = new Point(cx - half, cy + half);           // Bottom-left front
        
        // Back face vertices (further from viewer, offset for 3D effect)
        vertices[4] = new Point(cx - half - depth, cy - half - depth);  // Top-left back
        vertices[5] = new Point(cx + half - depth, cy - half - depth);  // Top-right back
        vertices[6] = new Point(cx + half - depth, cy + half - depth);  // Bottom-right back
        vertices[7] = new Point(cx - half - depth, cy + half - depth);  // Bottom-left back
        
        return vertices;
    }
    
    /**
     * Get the color of this cube.
     * 
     * @return the color of the cube
     */
    @Override
    public Color getColor() {
        return color;
    }
    
    /**
     * Get the center point of this cube.
     * 
     * @return the center point
     */
    public Point getCenter() {
        return center;
    }
    
    /**
     * Get the size of this cube.
     * 
     * @return the size
     */
    public int getSize() {
        return size;
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
        return String.format("Cube[center=%s, size=%d]", center, size);
    }
}
