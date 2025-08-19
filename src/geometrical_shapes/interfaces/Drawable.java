package geometrical_shapes.interfaces;

import java.awt.Color;

/**
 * Interface for geometric shapes that can be drawn on a Displayable surface.
 * All drawable objects must provide a way to draw themselves and specify their color.
 */
public interface Drawable {
    /**
     * Draw this shape on the given displayable surface.
     * 
     * @param displayable the surface to draw on
     */
    void draw(Displayable displayable);
    
    /**
     * Get the color of this drawable shape.
     * 
     * @return the color of the shape
     */
    Color getColor();
}
