package lab04;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

class CalculatorTest {

    @Test
    void testALessThanTwo() {
        Calculator calc = new Calculator();
        assertEquals(-5, calc.complexAdd(1, 4));
    }

    @Test
    void testAGreaterOrEqualTwo() {
        Calculator calc = new Calculator();
        assertEquals(6, calc.complexAdd(2, 4));
    }
}
/*
Mutation Testing Results:

Initial tests achieved 100% line and branch coverage but did not kill all mutants.
Additional tests were required to distinguish between mutations such as:
- Replacing < with <=
- Removing the negation in (a + b) * -1

After adding boundary value tests (a = 1, a = 2), all mutants were killed. This resulted
100% mutation coverage.


*/
