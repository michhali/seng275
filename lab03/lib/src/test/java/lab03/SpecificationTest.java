package lab03;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SpecificationTest {

    @BeforeEach
    void resetDefinitionToHD() {
        // The spec says default is HD if setDefinition has not been called.
        // But tests can run in any order, so we reset to HD for consistency.
        Specification.setDefinition(0);
    }

    // insideDisplayArea(x, y)
    //
    // Spec summary:
    // - True when x and y are inside the display pixel area
    // - (0,0) is top-left and is inside
    // - Edges are inside
    // - HD mode: width 1280, height 720 (default)
    // - FHD mode: width 1920, height 1080 (after setDefinition(1))

    @Test
    void insideDisplayArea_HD_topLeftCornerIsInside() {
        assertTrue(Specification.insideDisplayArea(0, 0));
    }

    @Test
    void insideDisplayArea_HD_bottomRightEdgeIsInside() {
        // In HD, max valid is (1279, 719) because edges are included
        assertTrue(Specification.insideDisplayArea(1279, 719));
    }

    @Test
    void insideDisplayArea_HD_xJustOutsideRightEdgeIsOutside() {
        assertFalse(Specification.insideDisplayArea(1280, 0));
    }

    @Test
    void insideDisplayArea_HD_yJustOutsideBottomEdgeIsOutside() {
        assertFalse(Specification.insideDisplayArea(0, 720));
    }

    @Test
    void insideDisplayArea_HD_negativeCoordinatesAreOutside() {
        assertAll(
                () -> assertFalse(Specification.insideDisplayArea(-1, 0)),
                () -> assertFalse(Specification.insideDisplayArea(0, -1))
        );
    }

    @Test
    void insideDisplayArea_FHD_edgesFollow1920x1080() {
        Specification.setDefinition(1); // FHD: 1920 x 1080
        assertAll(
                () -> assertTrue(Specification.insideDisplayArea(1919, 1079)),
                () -> assertFalse(Specification.insideDisplayArea(1920, 0)),
                () -> assertFalse(Specification.insideDisplayArea(0, 1080))
        );
    }

    // messageIsValid(input, motorcycle)
    //
    // Spec summary:
    // - Must be 2 to 6 letters or letters and numbers
    // - Can include blank spaces or hyphens, but each space or hyphen must have
    //   a letter or number before and after it
    // - If it contains a hyphen:
    //     - max 7 characters (or max 6 for motorcycle)
    // - Must not be composed only of numbers
    // - Ignore other ICBC rules not listed

    @Test
    void messageIsValid_simpleTwoLettersIsValid() {
        assertTrue(Specification.messageIsValid("AB", false));
    }

    @Test
    void messageIsValid_tooShortIsInvalid() {
        assertFalse(Specification.messageIsValid("A", false));
    }

    @Test
    void messageIsValid_lettersAndNumbersUpToSixIsValid() {
        assertTrue(Specification.messageIsValid("ABC123", false)); // length 6
    }

    @Test
    void messageIsValid_noHyphenMoreThanSixIsInvalid() {
        // Spec says 2 to 6 letters or letters and numbers
        assertFalse(Specification.messageIsValid("ABC1234", false)); // length 7, no hyphen
    }

    @Test
    void messageIsValid_onlyNumbersIsInvalid() {
        assertFalse(Specification.messageIsValid("12", false));
    }

    @Test
    void messageIsValid_spaceMustBeSurroundedByAlnum() {
        assertAll(
                () -> assertTrue(Specification.messageIsValid("A B", false)),   // valid: space between letters
                () -> assertFalse(Specification.messageIsValid("AB ", false)),  // invalid: ends with space
                () -> assertFalse(Specification.messageIsValid(" AB", false))   // invalid: starts with space
        );
    }

    @Test
    void messageIsValid_hyphenMustBeSurroundedByAlnum() {
        assertAll(
                () -> assertTrue(Specification.messageIsValid("AB-12", false)),   // valid: hyphen between alnum
                () -> assertFalse(Specification.messageIsValid("-AB12", false)),  // invalid: starts with hyphen
                () -> assertFalse(Specification.messageIsValid("AB12-", false))   // invalid: ends with hyphen
        );
    }

    @Test
    void messageIsValid_doubleHyphenIsInvalid() {
        // Each hyphen must have a letter/number on both sides, so "--" breaks the rule.
        assertFalse(Specification.messageIsValid("AB--C", false));
    }

    @Test
    void messageIsValid_hyphenAllowsUpToSevenNonMotorcycle() {
        // With a hyphen, spec allows up to 7 characters (non-motorcycle).
        assertTrue(Specification.messageIsValid("ABC-123", false)); // length 7
    }

    @Test
    void messageIsValid_hyphenMaxLengthMotorcycleIsSix() {
        // With a hyphen, motorcycle max is 6, so length 7 should be invalid.
        assertFalse(Specification.messageIsValid("ABC-123", true)); // length 7
    }

    @Test
    void messageIsValid_invalidCharacterIsInvalid() {
        // Spec only allows letters, numbers, spaces, hyphens
        assertFalse(Specification.messageIsValid("AB@12", false));
    }
}
