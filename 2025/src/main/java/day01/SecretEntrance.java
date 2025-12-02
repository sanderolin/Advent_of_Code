package day01;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

public class SecretEntrance {

    private static final int DIAL_SIZE = 100;
    private static final int START_POSITION = 50;

    record Rotation(boolean turnLeft, int distance) {}

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
        int solution = 0;
        int dialPosition = START_POSITION;
        for (String line : lines) {
            Rotation rotation = parseRotation(line);
            int direction = rotation.turnLeft() ? -1 : 1;
            int newPosition = dialPosition + (rotation.distance() * direction);
            dialPosition = Math.floorMod(newPosition, DIAL_SIZE);
            if (dialPosition == 0) solution++;
        }
        System.out.printf("Part 1: %s%n", solution);
    }

    // Dirty brute force :)
    private static void solvePart2(List<String> lines) {
        int solution = 0;
        int dialPosition = START_POSITION;
        for (String line : lines) {
            Rotation rotation = parseRotation(line);
            int step = rotation.turnLeft() ? -1 : 1;
            for (int i = 0; i < rotation.distance(); i++) {
                dialPosition = Math.floorMod(dialPosition + step, DIAL_SIZE);
                if (dialPosition == 0) {
                    solution++;
                }
            }
        }
        System.out.printf("Part 2: %s%n", solution);
    }

    private static Rotation parseRotation(String line) {
        boolean turnLeft = line.charAt(0) == 'L';
        int distance = Integer.parseInt(line.substring(1));
        return new Rotation(turnLeft, distance);
    }

    private static List<String> readFile() throws IOException {
        return Files.readAllLines(Path.of("src/main/resources/day01.txt"));
    }
}
