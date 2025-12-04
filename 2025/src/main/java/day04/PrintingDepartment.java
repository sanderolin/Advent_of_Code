package day04;

import lombok.RequiredArgsConstructor;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class PrintingDepartment {

    @RequiredArgsConstructor
    enum Direction {
        NORTH_WEST(-1, -1),
        NORTH(0, -1),
        NORTH_EAST(1, -1),
        EAST(1, 0),
        WEST(-1, 0),
        SOUTH_WEST(-1, 1),
        SOUTH(0, 1),
        SOUTH_EAST(1, 1);

        final int dx;
        final int dy;
    }

    public static void main(String[] args) {
        try {
            List<char[]> lines = readFile();
            System.out.printf("Part 1: %d%n", solvePart1(lines, false));
            System.out.printf("Part 2: %d%n", solvePart2(lines));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private static int solvePart1(List<char[]> lines, boolean shouldRemovePaperRoll) {
        int solution = 0;
        for (int yPos = 0; yPos < lines.size(); yPos++) {
            for (int xPos = 0; xPos < lines.get(yPos).length; xPos++) {
                if (!doesPositionContainPaperRoll(lines, xPos, yPos)) continue;
                if (canForkliftAccessPaperRoll(lines, xPos, yPos)) {
                    solution++;
                    if (shouldRemovePaperRoll) lines.get(yPos)[xPos] = 'x';
                }
            }
        }
        return solution;
    }

    private static boolean canForkliftAccessPaperRoll(List<char[]> lines, int xPos, int yPos) {
        int numberOfPaperRollsSurroundingCurrentPosition = 0;
        for (Direction direction : Direction.values()) {
            int neighbourX = xPos + direction.dx;
            int neighbourY = yPos + direction.dy;

            if (!isValidCoordinate(lines, neighbourX, neighbourY)) continue;
            if (doesPositionContainPaperRoll(lines, neighbourX, neighbourY)) numberOfPaperRollsSurroundingCurrentPosition++;
        }
        return numberOfPaperRollsSurroundingCurrentPosition < 4;
    }

    private static boolean isValidCoordinate(List<char[]> lines, int x, int y) {
        return y >= 0 && y < lines.size() && x >= 0 && x < lines.get(y).length;
    }

    private static boolean doesPositionContainPaperRoll(List<char[]> lines, int x, int y) {
        return lines.get(y)[x] == '@';
    }

    private static int solvePart2(List<char[]> lines) {
        int solution = 0;
        int numberOfPaperRollsRemovedCurrentIteration = -1;
        while (numberOfPaperRollsRemovedCurrentIteration != 0) {
            numberOfPaperRollsRemovedCurrentIteration = solvePart1(lines, true);
            solution += numberOfPaperRollsRemovedCurrentIteration;
        }
        return solution;
    }

    private static List<char[]> readFile() throws IOException {
        List<char[]> result = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader("src/main/resources/day04.txt"))) {
            String line;
            while ((line = br.readLine()) != null) {
                result.add(line.toCharArray());;
            }
        }
        return result;
    }
}
