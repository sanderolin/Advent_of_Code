package day02;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class GiftShop {

    record Range(long start, long end) {}

    public static void main(String[] args) {
        try {
            String lines = readFile();
            List<Range> ranges = parseRanges(lines);
            solvePart1(ranges);
            solvePart2(ranges);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private static void solvePart1(List<Range> lines) {
        long solution = 0;
        for (Range range : lines) {
            for (long numberInRange = range.start(); numberInRange <= range.end(); numberInRange++) {
                if (hasEvenNumberOfDigits(numberInRange) && areDigitsInFirstHalfRepeatedInSecondHalf(numberInRange)) {
                    solution += numberInRange;
                }
            }
        }
        System.out.printf("Part 1: %s%n", solution);
    }

    private static boolean areDigitsInFirstHalfRepeatedInSecondHalf(long n) {
        String longAsString = Long.toString(n);
        String[] split = {
                longAsString.substring(0, longAsString.length()/2),
                longAsString.substring(longAsString.length()/2)
        };
        return split[0].equals(split[1]);
    }

    private static boolean hasEvenNumberOfDigits(long n) {
        return Long.toString(n).length() % 2 == 0;
    }

    private static void solvePart2(List<Range> lines) {
        long solution = 0;
        for (Range range : lines) {
            for (long i = range.start(); i <= range.end(); i++) {
                if (hasMoreThanTwoRepeatingPatterns(i)) {
                    solution += i;
                }
            }
        }
        System.out.printf("Part 2: %s%n", solution);
    }

    private static boolean hasMoreThanTwoRepeatingPatterns(long n) {
        String longAsString = Long.toString(n);
        int lengthOfNumber = longAsString.length();
        for (int patternLength = 1; patternLength <= lengthOfNumber / 2; patternLength++) {
            if (lengthOfNumber % patternLength != 0) {
                continue;
            }
            boolean matches = true;
            for (int i = patternLength; i < lengthOfNumber; i++) {
                if (longAsString.charAt(i % patternLength) != longAsString.charAt(i)) {
                    matches = false;
                    break;
                }
            }
            if (matches) return true;
        }
        return false;
    }

    private static String readFile() throws IOException {
        return Files.readAllLines(Path.of("src/main/resources/day02.txt")).getFirst();
    }

    private static List<Range> parseRanges(String lines) throws IOException {
        List<Range> ranges = new ArrayList<>();
        String[] rangeSplit = lines.split(",");
        for (String range : rangeSplit) {
            String[] parts = range.split("-");
            long start = Long.parseLong(parts[0]);
            long end = Long.parseLong(parts[1]);
            ranges.add(new Range(start, end));
        }
        return ranges;
    }
}
