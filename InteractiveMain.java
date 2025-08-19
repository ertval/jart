import geometrical_shapes.Image;
import geometrical_shapes.shapes.*;
import geometrical_shapes.bonus.*;
import java.awt.Color;
import java.util.Scanner;

/**
 * Interactive version of the Jart application that allows user customization.
 * Users can specify background color, number of shapes, and optional text display.
 */
public class InteractiveMain {
    private static Scanner scanner = new Scanner(System.in);
    
    public static void main(String[] args) {
        System.out.println("🎨 Welcome to Jart (Java Art) - Interactive Mode!");
        System.out.println("================================================\n");
        
        try {
            // Get user preferences
            int width = getIntInput("Enter image width (default 1000): ", 1000, 100, 5000);
            int height = getIntInput("Enter image height (default 1000): ", 1000, 100, 5000);
            
            Color backgroundColor = getBackgroundColor();
            int numShapes = getIntInput("How many random shapes to generate (default 50): ", 50, 0, 500);
            String centerText = getCenterText();
            
            // Create image with user specifications
            Image image = new Image(width, height, backgroundColor);
            
            System.out.println("\n🎨 Creating your custom artwork...");
            
            // Draw the standard demo shapes
            drawDemoShapes(image);
            
            // Generate user-specified number of random shapes
            generateRandomShapes(image, numShapes);
            
            // Add center text if requested
            if (!centerText.isEmpty()) {
                Color textColor = getContrastColor(backgroundColor);
                int fontSize = Math.min(width, height) / 20; // Dynamic font size
                image.drawCenteredText(centerText, textColor, fontSize);
            }
            
            // Save the image
            String filename = "custom_image.png";
            image.save(filename);
            image.dispose();
            
            System.out.println("✅ Artwork created successfully: " + filename);
            System.out.println("📐 Dimensions: " + width + "x" + height + " pixels");
            System.out.println("🎨 Background: " + colorToString(backgroundColor));
            System.out.println("🔢 Random shapes: " + numShapes);
            if (!centerText.isEmpty()) {
                System.out.println("📝 Center text: \"" + centerText + "\"");
            }
            
        } catch (Exception e) {
            System.err.println("❌ Error creating artwork: " + e.getMessage());
        } finally {
            scanner.close();
        }
    }
    
    /**
     * Get integer input from user with validation and default value.
     */
    private static int getIntInput(String prompt, int defaultValue, int min, int max) {
        while (true) {
            System.out.print(prompt);
            String input = scanner.nextLine().trim();
            
            if (input.isEmpty()) {
                return defaultValue;
            }
            
            try {
                int value = Integer.parseInt(input);
                if (value >= min && value <= max) {
                    return value;
                } else {
                    System.out.println("⚠️  Please enter a value between " + min + " and " + max);
                }
            } catch (NumberFormatException e) {
                System.out.println("⚠️  Please enter a valid number");
            }
        }
    }
    
    /**
     * Get background color choice from user.
     */
    private static Color getBackgroundColor() {
        System.out.println("\nChoose background color:");
        System.out.println("1. White (default)");
        System.out.println("2. Black");
        System.out.println("3. Light Gray");
        System.out.println("4. Dark Blue");
        System.out.println("5. Dark Green");
        System.out.println("6. Maroon");
        System.out.println("7. Custom RGB");
        
        while (true) {
            System.out.print("Enter choice (1-7): ");
            String input = scanner.nextLine().trim();
            
            if (input.isEmpty()) input = "1";
            
            switch (input) {
                case "1": return Color.WHITE;
                case "2": return Color.BLACK;
                case "3": return Color.LIGHT_GRAY;
                case "4": return new Color(25, 25, 112); // Midnight Blue
                case "5": return new Color(0, 100, 0);   // Dark Green
                case "6": return new Color(128, 0, 0);   // Maroon
                case "7": return getCustomColor();
                default:
                    System.out.println("⚠️  Please enter 1-7");
            }
        }
    }
    
    /**
     * Get custom RGB color from user.
     */
    private static Color getCustomColor() {
        System.out.println("Enter RGB values (0-255):");
        int r = getIntInput("Red: ", 128, 0, 255);
        int g = getIntInput("Green: ", 128, 0, 255);
        int b = getIntInput("Blue: ", 128, 0, 255);
        return new Color(r, g, b);
    }
    
    /**
     * Get center text from user.
     */
    private static String getCenterText() {
        System.out.print("\nEnter text to display in center (press Enter to skip): ");
        String text = scanner.nextLine().trim();
        
        if (text.length() > 50) {
            System.out.println("⚠️  Text truncated to 50 characters");
            text = text.substring(0, 50);
        }
        
        return text;
    }
    
    /**
     * Draw the standard demo shapes from the original Main class.
     */
    private static void drawDemoShapes(Image image) {
        // Create and draw a rectangle
        Rectangle rectangle = new Rectangle(new Point(50, 50), new Point(300, 200));
        rectangle.draw(image);
        
        // Create and draw a triangle
        Triangle triangle = new Triangle(
            new Point(100, 100), 
            new Point(image.getWidth() - 100, image.getHeight() - 100), 
            new Point(100, image.getHeight() - 100)
        );
        triangle.draw(image);
        
        // Add some lines for visual interest
        Line line1 = new Line(new Point(0, 0), new Point(image.getWidth(), image.getHeight()));
        line1.draw(image);
        
        Line line2 = new Line(new Point(image.getWidth(), 0), new Point(0, image.getHeight()));
        line2.draw(image);
    }
    
    /**
     * Generate random shapes based on user input.
     */
    private static void generateRandomShapes(Image image, int numShapes) {
        if (numShapes <= 0) return;
        
        int width = image.getWidth();
        int height = image.getHeight();
        
        for (int i = 0; i < numShapes; i++) {
            // Randomly choose shape type
            int shapeType = (int) (Math.random() * 7); // 0-6 for different shapes
            
            switch (shapeType) {
                case 0:
                    Circle.random(width, height).draw(image);
                    break;
                case 1:
                    Rectangle.random(width, height).draw(image);
                    break;
                case 2:
                    Triangle.random(width, height).draw(image);
                    break;
                case 3:
                    Line.random(width, height).draw(image);
                    break;
                case 4:
                    Point.random(width, height).draw(image);
                    break;
                case 5:
                    Pentagon.random(width, height).draw(image);
                    break;
                case 6:
                    Cube.random(width, height).draw(image);
                    break;
            }
        }
    }
    
    /**
     * Get a contrasting color for text based on background.
     */
    private static Color getContrastColor(Color backgroundColor) {
        // Calculate luminance using relative luminance formula
        double luminance = (0.299 * backgroundColor.getRed() + 
                          0.587 * backgroundColor.getGreen() + 
                          0.114 * backgroundColor.getBlue()) / 255.0;
        
        // Return white text for dark backgrounds, black for light backgrounds
        return luminance > 0.5 ? Color.BLACK : Color.WHITE;
    }
    
    /**
     * Convert color to readable string.
     */
    private static String colorToString(Color color) {
        if (color.equals(Color.WHITE)) return "White";
        if (color.equals(Color.BLACK)) return "Black";
        if (color.equals(Color.LIGHT_GRAY)) return "Light Gray";
        return String.format("RGB(%d, %d, %d)", color.getRed(), color.getGreen(), color.getBlue());
    }
}
