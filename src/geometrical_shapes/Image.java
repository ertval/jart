package geometrical_shapes;

import geometrical_shapes.interfaces.Displayable;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

/**
 * Image class that implements Displayable interface.
 * Uses Java AWT to create and manipulate a BufferedImage for drawing operations.
 */
public class Image implements Displayable {
    private BufferedImage bufferedImage;
    private Graphics2D graphics;
    private int width;
    private int height;
    
    /**
     * Create a new Image with specified dimensions.
     * 
     * @param width the width of the image
     * @param height the height of the image
     */
    public Image(int width, int height) {
        this(width, height, Color.WHITE);
    }
    
    /**
     * Create a new Image with specified dimensions and background color.
     * 
     * @param width the width of the image
     * @param height the height of the image
     * @param backgroundColor the background color of the image
     */
    public Image(int width, int height, Color backgroundColor) {
        this.width = width;
        this.height = height;
        this.bufferedImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        this.graphics = bufferedImage.createGraphics();
        
        // Set up graphics for better rendering
        graphics.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        graphics.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
        graphics.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
        
        // Fill with specified background color
        graphics.setColor(backgroundColor);
        graphics.fillRect(0, 0, width, height);
    }
    
    /**
     * Display a pixel at the specified coordinates with the given color.
     * 
     * @param x the x-coordinate
     * @param y the y-coordinate
     * @param color the color to display
     */
    @Override
    public void display(int x, int y, Color color) {
        if (x >= 0 && x < width && y >= 0 && y < height) {
            bufferedImage.setRGB(x, y, color.getRGB());
        }
    }
    
    /**
     * Save the current image to a file.
     * 
     * @param filename the name of the file to save to
     */
    @Override
    public void save(String filename) {
        try {
            File outputFile = new File(filename);
            ImageIO.write(bufferedImage, "png", outputFile);
        } catch (IOException e) {
            System.err.println("Error saving image: " + e.getMessage());
        }
    }
    
    /**
     * Get the width of the image.
     * 
     * @return the width in pixels
     */
    public int getWidth() {
        return width;
    }
    
    /**
     * Get the height of the image.
     * 
     * @return the height in pixels
     */
    public int getHeight() {
        return height;
    }
    
    /**
     * Get the Graphics2D object for advanced drawing operations.
     * 
     * @return the Graphics2D object
     */
    public Graphics2D getGraphics() {
        return graphics;
    }
    
    /**
     * Draw text in the center of the image.
     * 
     * @param text the text to draw
     * @param color the color of the text
     * @param fontSize the font size
     */
    public void drawCenteredText(String text, Color color, int fontSize) {
        Font font = new Font("Arial", Font.BOLD, fontSize);
        graphics.setFont(font);
        graphics.setColor(color);
        
        FontMetrics metrics = graphics.getFontMetrics(font);
        int textWidth = metrics.stringWidth(text);
        int textHeight = metrics.getHeight();
        
        int x = (width - textWidth) / 2;
        int y = (height - textHeight) / 2 + metrics.getAscent();
        
        // Draw text with shadow for better visibility
        graphics.setColor(Color.BLACK);
        graphics.drawString(text, x + 2, y + 2);
        graphics.setColor(color);
        graphics.drawString(text, x, y);
    }
    
    /**
     * Dispose of graphics resources.
     */
    public void dispose() {
        if (graphics != null) {
            graphics.dispose();
        }
    }
}
