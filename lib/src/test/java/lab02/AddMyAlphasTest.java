package lab02;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class AddMyAlphasTest {

    @Test
    void emptyStringReturnsZero() {
        AddMyAlphas a = new AddMyAlphas();
        assertEquals(0, a.add(""));
    }

    @Test
    void singleNumberReturnsValue() {
        AddMyAlphas a = new AddMyAlphas();
        assertEquals(5, a.add("5"));
    }

    @Test
    void twoNumbersReturnSum() {
        AddMyAlphas a = new AddMyAlphas();
        assertEquals(3, a.add("1,2"));
    }

    @Test
    void multipleNumbersReturnSum() {
        AddMyAlphas a = new AddMyAlphas();
        assertEquals(10, a.add("1,2,3,4"));
    }

    @Test
    void newLinesAreAllowed() {
        AddMyAlphas a = new AddMyAlphas();
        assertEquals(6, a.add("1\n2,3"));
    }

    @Test
    void negativesThrowException() {
        AddMyAlphas a = new AddMyAlphas();
        Exception e = assertThrows(IllegalArgumentException.class,
                () -> a.add("2,-4,3,-5"));
        assertEquals("Negatives not allowed: -4,-5", e.getMessage());
    }

    @Test
    void numbersGreaterThan1000AreIgnored() {
        AddMyAlphas a = new AddMyAlphas();
        assertEquals(2, a.add("1001,2"));
    }

    @Test
    void customDelimiterWorks() {
        AddMyAlphas a = new AddMyAlphas();
        assertEquals(3, a.add("//;\n1;2"));
    }
}
