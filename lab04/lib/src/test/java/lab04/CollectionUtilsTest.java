package lab04;

import static org.junit.jupiter.api.Assertions.*;

import java.util.*;
import java.util.stream.*;
import org.junit.jupiter.api.*;
import org.junit.jupiter.params.*;
import org.junit.jupiter.params.provider.*;

class CollectionUtilsTest {

    @Test
    void testContainsAnyWhenFirstSmaller() {
        Collection<Integer> c1 = List.of(1);
        Collection<Integer> c2 = List.of(1, 2, 3);

        assertTrue(CollectionUtils.containsAny(c1, c2));
    }
    @Test
    void testFirstCollectionSmallerNoMatch() {
        Collection<Integer> c1 = List.of(1);
        Collection<Integer> c2 = List.of(2, 3, 4);

        assertFalse(CollectionUtils.containsAny(c1, c2));
    }
    @Test
    void testContainsAnyWhenSecondSmaller() {
        Collection<Integer> c1 = List.of(1, 2, 3);
        Collection<Integer> c2 = List.of(2);

        assertTrue(CollectionUtils.containsAny(c1, c2));
    }

    @Test
    void testContainsAnyReturnsFalse() {
        Collection<Integer> c1 = List.of(1, 2);
        Collection<Integer> c2 = List.of(3, 4);

        assertFalse(CollectionUtils.containsAny(c1, c2));
    }
}
