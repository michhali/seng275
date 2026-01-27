import java.util.Objects;

public class Rectangle {

    private int x;
    private int y;
    private int width;
    private int height;

    // Default constructor
    public Rectangle() {
        this(0, 0, 1, 1);
    }

    // Full constructor
    public Rectangle(int x, int y, int width, int height) {
        validateDimension(width);
        validateDimension(height);
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
    }

    // Getters
    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    // Setters
    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

    public void setWidth(int width) {
        validateDimension(width);
        this.width = width;
    }

    public void setHeight(int height) {
        validateDimension(height);
        this.height = height;
    }

    // Area
    public int area() {
        return width * height;
    }

    // Containment
    public boolean contains(Rectangle other) {
        if (other == null) return false;

        return this.x <= other.x
                && this.y <= other.y
                && this.x + this.width >= other.x + other.width
                && this.y + this.height >= other.y + other.height;
    }

    // Equality
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Rectangle)) return false;
        Rectangle r = (Rectangle) o;
        return x == r.x && y == r.y && width == r.width && height == r.height;
    }

    // HashMap support
    @Override
    public int hashCode() {
        return Objects.hash(x, y, width, height);
    }

    private void validateDimension(int value) {
        if (value <= 0) {
            throw new IllegalArgumentException("Width and height must be > 0");
        }
    }
}
