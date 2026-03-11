package lab04;

import static org.junit.jupiter.api.Assertions.*;

import java.util.*;
import java.util.stream.*;
import org.junit.jupiter.api.*;
import org.junit.jupiter.params.*;
import org.junit.jupiter.params.provider.*;

class WordUtilitiesTest {

    @Test
    void testNullString() {
        assertNull(WordUtilities.swapCase(null));
    }
    @Test
    void testNullInput() {
        assertNull(WordUtilities.swapCase(null));
    }

    @Test
    void testEmptyString() {
        assertEquals("", WordUtilities.swapCase(""));
    }

    @Test
    void testAllUpperCase() {
        assertEquals("abc", WordUtilities.swapCase("ABC"));
    }

    @Test
    void testLowerCaseWithWhitespace() {
        assertEquals("Hello World", WordUtilities.swapCase("hELLO wORLD"));
    }

    @Test
    void testMixedCase() {
        assertEquals("tEST cASE", WordUtilities.swapCase("Test Case"));
    }
}
