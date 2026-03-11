package lab03;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;



import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class BoundaryTest {


    // isUnsafe(int volume)


    @Test
    void isUnsafe() {
        assertTrue(Boundary.isUnsafe(86));
    }

    @Test
    void isNotUnsafe() {
        assertFalse(Boundary.isUnsafe(85));
    }


    // isComfortable(int temperature)
    // Comfortable range: 5 to 20 inclusive


    @Test
    void temperatureJustBelowLowerBoundaryIsNotComfortable() {
        assertFalse(Boundary.isComfortable(4));
    }

    @Test
    void temperatureAtLowerBoundaryIsComfortable() {
        assertTrue(Boundary.isComfortable(5));
    }

    @Test
    void temperatureAtUpperBoundaryIsComfortable() {
        assertTrue(Boundary.isComfortable(20));
    }

    @Test
    void temperatureJustAboveUpperBoundaryIsNotComfortable() {
        assertFalse(Boundary.isComfortable(21));
    }


    // elevatorsRequired(int storeys)

    @Test
    void storeysBelowTwoRequireZeroElevators() {
        assertEquals(0, Boundary.elevatorsRequired(1));
    }

    @Test
    void storeysAtTwoRequireOneElevator() {
        assertEquals(1, Boundary.elevatorsRequired(2));
    }

    @Test
    void storeysAtFiveRequireOneElevator() {
        assertEquals(1, Boundary.elevatorsRequired(5));
    }

    @Test
    void storeysAtSixRequireTwoElevators() {
        assertEquals(2, Boundary.elevatorsRequired(6));
    }


    // percentageToLetterGrade(double percent)

    @Test
    void percentJustBelowFailBoundaryIsF() {
        assertEquals("F", Boundary.percentageToLetterGrade(49.99));
    }

    @Test
    void percentAtFailBoundaryIsD() {
        assertEquals("D", Boundary.percentageToLetterGrade(50));
    }

    @Test
    void percentAtUpperBoundaryIsAPlus() {
        assertEquals("A+", Boundary.percentageToLetterGrade(100));
    }

    @Test
    void percentBelowZeroThrowsException() {
        assertThrows(IllegalArgumentException.class,
                () -> Boundary.percentageToLetterGrade(-1));
    }

    @Test
    void percentAboveHundredThrowsException() {
        assertThrows(IllegalArgumentException.class,
                () -> Boundary.percentageToLetterGrade(101));
    }
}
