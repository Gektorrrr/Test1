import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;

public class Main {
    public static void main(String[] args) {
        String filePath = "10m.txt";

        ArrayList<Integer> numbers = readNumbersFromFile(filePath);

        if (numbers != null && !numbers.isEmpty()) {
            int max = Collections.max(numbers);
            int min = Collections.min(numbers);
            int longestIncreasingSequence = findLongestIncreasingSequence(numbers);
            int longestDecreasingSequence = findLongestDecreasingSequence(numbers);

            Collections.sort(numbers);
            double median;
            if (numbers.size() % 2 == 0) {
                median = (double) (numbers.get(numbers.size() / 2 - 1) + numbers.get(numbers.size() / 2)) / 2;
            } else {
                median = (double) numbers.get(numbers.size() / 2);
            }

            double sum = 0;
            for (int num : numbers) {
                sum += num;
            }
            double average = sum / numbers.size();


            System.out.println("Мax number: " + max);
            System.out.println("Мin number: " + min);
            System.out.println("Median: " + median);
            System.out.println("Average: " + average);
            System.out.println("The largest sequence of increasing numbers: " + longestIncreasingSequence);
            System.out.println("The smallest sequence of increasing numbers: " + longestDecreasingSequence);
        } else {
            System.out.println("Error");
        }
    }

    private static ArrayList<Integer> readNumbersFromFile(String filePath) {
        ArrayList<Integer> numbers = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                numbers.add(Integer.parseInt(line));
            }
        } catch (IOException | NumberFormatException e) {
            e.printStackTrace();
        }
        return numbers;
    }

    private static int findLongestIncreasingSequence(ArrayList<Integer> numbers) {
        int maxLength = 1;
        int currentLength = 1;
        for (int i = 1; i < numbers.size(); i++) {
            if (numbers.get(i) > numbers.get(i - 1)) {
                currentLength++;
            } else {
                maxLength = Math.max(maxLength, currentLength);
                currentLength = 1;
            }
        }
        return Math.max(maxLength, currentLength);
    }

    private static int findLongestDecreasingSequence(ArrayList<Integer> numbers) {
        int minLength = 1;
        int currentLength = 1;
        for (int i = 1; i < numbers.size(); i++) {
            if (numbers.get(i) < numbers.get(i - 1)) {
                currentLength++;
            } else {
                minLength = Math.max(minLength, currentLength);
                currentLength = 1;
            }
        }
        return Math.max(minLength, currentLength);
    }
}
