package geometrical_shapes.interfaces;

import java.awt.Color;

/**
 * Interface for objects that can display graphics and save images.
 * Provides methods for rendering pixels and saving the display to a file.
 */
public interface Displayable {
    /**
     * Display a pixel at the specified coordinates with the given color.
     * 
     * @param x the x-coordinate
     * @param y the y-coordinate
     * @param color the color to display
     */
    void display(int x, int y, Color color);
    
    /**
     * Save the current display to a file.
     * 
     * @param filename the name of the file to save to
     */
    void save(String filename);
}
