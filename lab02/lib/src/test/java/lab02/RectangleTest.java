package lab02;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class RectangleTest {

    @Test
    void defaultRectangleHasUnitSizeAtOrigin() {
        Rectangle r = new Rectangle();
        assertEquals(0, r.getX());
        assertEquals(0, r.getY());
        assertEquals(1, r.getWidth());
        assertEquals(1, r.getHeight());
    }

    @Test
    void customRectangleStoresValues() {
        Rectangle r = new Rectangle(5, 5, 10, 10);
        assertEquals(5, r.getX());
        assertEquals(5, r.getY());
        assertEquals(10, r.getWidth());
        assertEquals(10, r.getHeight());
    }

    @Test
    void settersUpdateValues() {
        Rectangle r = new Rectangle(1, 1, 2, 2);
        r.setX(3);
        r.setY(4);
        r.setWidth(5);
        r.setHeight(6);

        assertEquals(3, r.getX());
        assertEquals(4, r.getY());
        assertEquals(5, r.getWidth());
        assertEquals(6, r.getHeight());
    }

    @Test
    void invalidWidthOrHeightThrowsException() {
        assertThrows(IllegalArgumentException.class, () -> new Rectangle(0, 0, 0, 1));
        assertThrows(IllegalArgumentException.class, () -> new Rectangle(0, 0, 1, 0));

        Rectangle r = new Rectangle(1, 1, 2, 2);
        assertThrows(IllegalArgumentException.class, () -> r.setWidth(-1));
        assertThrows(IllegalArgumentException.class, () -> r.setHeight(0));
    }

    @Test
    void areaIsCorrect() {
        Rectangle r = new Rectangle(0, 0, 3, 4);
        assertEquals(12, r.area());
    }

    @Test
    void rectanglesEqualityWorks() {
        Rectangle a = new Rectangle(1, 2, 3, 4);
        Rectangle b = new Rectangle(1, 2, 3, 4);
        Rectangle c = new Rectangle(0, 0, 3, 4);

        assertEquals(a, b);
        assertNotEquals(a, c);
    }

    @Test
    void containsWorksCorrectly() {
        Rectangle outer = new Rectangle(0, 0, 10, 10);
        Rectangle inner = new Rectangle(2, 2, 3, 3);
        Rectangle outside = new Rectangle(9, 9, 2, 2);

        assertTrue(outer.contains(inner));
        assertFalse(outer.contains(outside));
    }
}
