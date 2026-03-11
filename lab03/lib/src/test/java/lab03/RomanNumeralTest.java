package lab03;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

class RomanNumeralTest {

    RomanNumeral rn = new RomanNumeral();


    // Valid basic numerals

    @Test
    void singleSymbolValid() {
        assertEquals(1, rn.convert("I"));
        assertNotEquals(1, rn.convert("V")); // fail case
    }

    @Test
    void additiveNotationValid() {
        assertEquals(6, rn.convert("VI"));
        assertNotEquals(6, rn.convert("IV"));
    }

    @Test
    void subtractiveNotationValid() {
        assertEquals(9, rn.convert("IX"));
        assertNotEquals(9, rn.convert("XI"));
    }


    // Larger valid values

    @Test
    void complexRomanNumeralValid() {
        assertEquals(1842, rn.convert("MDCCCXLII"));
        assertNotEquals(1842, rn.convert("MDCCCLX"));
    }

    @Test
    void highestStandardValueValid() {
        // valid input
        assertEquals(3999, rn.convert("MMMCMXCIX"));

        // invalid input (4 Ms in a row should be rejected)
        assertThrows(IllegalArgumentException.class, () -> rn.convert("MMMM"));
    }


    // Invalid input classes

    @Test
    void invalidCharacters() {
        assertThrows(Exception.class, () -> rn.convert("ABC"));
    }

    @Test
    void lowercaseInputInvalid() {
        assertThrows(Exception.class, () -> rn.convert("ix"));
    }

    @Test
    void repeatedInvalidSymbol() {
        assertThrows(Exception.class, () -> rn.convert("VV"));
    }

    @Test
    void invalidSubtractivePattern() {
        assertThrows(Exception.class, () -> rn.convert("IC"));
    }

    @Test
    void emptyStringInvalid() {
        assertThrows(Exception.class, () -> rn.convert(""));
    }
}

