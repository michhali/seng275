package lab02;
import java.util.ArrayList;
import java.util.List;

public class AddMyAlphas {

    public int add(String numbers) {
        if (numbers == null || numbers.isEmpty()) {
            return 0;
        }

        String delimiter = ",|\n";
        String nums = numbers;

        // Custom delimiter support: //[delimiter]\n[numbers]
        if (numbers.startsWith("//")) {
            int newlineIndex = numbers.indexOf("\n");
            delimiter = numbers.substring(2, newlineIndex);
            nums = numbers.substring(newlineIndex + 1);
        }

        String[] parts = nums.split(delimiter);
        int sum = 0;
        List<Integer> negatives = new ArrayList<>();

        for (String part : parts) {
            if (part.isEmpty()) continue;

            int value = Integer.parseInt(part);

            if (value < 0) {
                negatives.add(value);
            } else if (value <= 1000) {
                sum += value;
            }
        }

        if (!negatives.isEmpty()) {
            StringBuilder message = new StringBuilder("Negatives not allowed: ");
            for (int i = 0; i < negatives.size(); i++) {
                message.append(negatives.get(i));
                if (i < negatives.size() - 1) {
                    message.append(",");
                }
            }
            throw new IllegalArgumentException(message.toString());
        }

        return sum;
    }
}
