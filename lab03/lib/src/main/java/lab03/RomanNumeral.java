package lab03;

import java.util.HashMap;
import java.util.Map;

public class RomanNumeral {

    private static final Map<Character, Integer> map = new HashMap<>();

    static {
        map.put('I', 1);
        map.put('V', 5);
        map.put('X', 10);
        map.put('L', 50);
        map.put('C', 100);
        map.put('D', 500);
        map.put('M', 1000);
    }

    public int convert(String s) {
        if (s == null || s.isEmpty()) {
            throw new IllegalArgumentException("Empty input");
        }

        int total = 0;
        int prevValue = 0;
        int repeatCount = 0;
        char prevChar = '\0';

        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);

            // Invalid character or lowercase
            if (!map.containsKey(c)) {
                throw new IllegalArgumentException("Invalid character");
            }

            int currentValue = map.get(c);

            // Repetition rules
            if (c == prevChar) {
                repeatCount++;

                // V, L, D cannot repeat
                if (c == 'V' || c == 'L' || c == 'D') {
                    throw new IllegalArgumentException("Invalid repetition");
                }

                // No symbol can repeat more than 3 times
                if (repeatCount >= 3) {
                    throw new IllegalArgumentException("Too many repetitions");
                }
            } else {
                repeatCount = 0;
            }

            // Subtractive rules
            if (currentValue > prevValue) {
                // Only I, X, C can be subtractive
                if (prevValue != 1 && prevValue != 10 && prevValue != 100 && prevValue != 0) {
                    throw new IllegalArgumentException("Invalid subtraction");
                }

                // Valid subtractive pairs
                if (!(
                        (prevValue == 1 && (currentValue == 5 || currentValue == 10)) ||
                                (prevValue == 10 && (currentValue == 50 || currentValue == 100)) ||
                                (prevValue == 100 && (currentValue == 500 || currentValue == 1000)) ||
                                prevValue == 0
                )) {
                    throw new IllegalArgumentException("Invalid subtractive pair");
                }

                total += currentValue - 2 * prevValue;
            } else {
                total += currentValue;
            }

            prevValue = currentValue;
            prevChar = c;
        }

        return total;
    }
}
