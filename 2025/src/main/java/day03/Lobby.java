package day03;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

public class Lobby {

    private static final int PART_1_SEQUENCE_LENGTH = 2;
    private static final int PART_2_SEQUENCE_LENGTH = 12;

    public static void main(String[] args) {
        try {
            List<String> lines = readFile();
            solvePart1(lines);
            solvePart2(lines);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private static void solvePart1(List<String> lines) {
        long solution = 0;
        for (String line : lines) {
            String best = maxSubsequenceOfLength(line, PART_1_SEQUENCE_LENGTH);
            solution += Integer.parseInt(best);
        }
        System.out.printf("Part 1: %s%n", solution);
    }

    private static void solvePart2(List<String> lines) {
        long solution = 0L;

        for (String line : lines) {
            String best = maxSubsequenceOfLength(line, PART_2_SEQUENCE_LENGTH);
            solution += Long.parseLong(best);
        }

        System.out.printf("Part 2: %s%n", solution);
    }

    private static String maxSubsequenceOfLength(String line, int sequenceLength) {
        int n = line.length();
        StringBuilder result = new StringBuilder(sequenceLength);
        int start = 0;

        for (int chosen = 0; chosen < sequenceLength; chosen++) {
            int remaining = sequenceLength - chosen;

            if (n - start == remaining) {
                result.append(line, start, n);
                break;
            }

            int maxIndex = n - remaining;
            char bestDigit = '0';
            int bestPos = start;

            for (int i = start; i <= maxIndex; i++) {
                char c = line.charAt(i);
                if (c > bestDigit) {
                    bestDigit = c;
                    bestPos = i;
                    if (bestDigit == '9') {
                        break;
                    }
                }
            }
            result.append(bestDigit);
            start = bestPos + 1;
        }

        return result.toString();
    }

    private static List<String> readFile() throws IOException {
        return Files.readAllLines(Path.of("src/main/resources/day03.txt"));
    }
}
