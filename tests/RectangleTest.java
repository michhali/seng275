import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class RectangleTest {

    @Test
    void customRectangleStoresValues() {
        Rectangle r = new Rectangle(5, 5, 10, 10);

        assertEquals(5, r.getX());
        assertEquals(5, r.getY());
        assertEquals(10, r.getWidth());
        assertEquals(10, r.getHeight());
    }
}
