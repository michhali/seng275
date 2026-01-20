import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class RectangleTest {

    @Test
    void testArea() {
        Rectangle r = new Rectangle(2, 3);
        assertEquals(6, r.area());
    }
}