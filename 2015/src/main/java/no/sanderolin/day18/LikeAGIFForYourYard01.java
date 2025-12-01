package no.sanderolin.day18;

import java.io.BufferedReader;
import java.io.FileReader;

public class LikeAGIFForYourYard01 {

    private static final int GRID_SIZE = 100;
    private static final int STEPS = 100;

    public static void solve() {
        char[][] grid = readFromFile();
        for (int i = 0; i < STEPS; i++) {
            grid = nextState(grid);
        }
        System.out.println(countLightsOn(grid));
    }

    private static char[][] nextState(char[][] grid) {
        char[][] newGrid = new char[GRID_SIZE][GRID_SIZE];
        for (int y = 0; y < GRID_SIZE; y++) {
            for (int x = 0; x < GRID_SIZE; x++) {
                int neighborsOn = countNeighbors(grid, x, y);
                if (grid[y][x] == '#') newGrid[y][x] = (neighborsOn == 2 || neighborsOn == 3) ? '#' : '.';
                else newGrid[y][x] = (neighborsOn == 3) ? '#' : '.';
            }
        }
        return newGrid;
    }

    private static int countNeighbors(char[][] grid, int x, int y) {
        int count = 0;
        for (int dy = -1; dy <= 1; dy++) {
            for (int dx = -1; dx <= 1; dx++) {
                if (dx == 0 && dy == 0) continue;
                int nx = x + dx;
                int ny = y + dy;
                if (nx >= 0 && nx < GRID_SIZE && ny >= 0 && ny < GRID_SIZE) {
                    if (grid[ny][nx] == '#') {
                        count++;
                    }
                }
            }
        }
        return count;
    }

    private static int countLightsOn(char[][] grid) {
        int count = 0;
        for (int y = 0; y < GRID_SIZE; y++) {
            for (int x = 0; x < GRID_SIZE; x++) {
                if (grid[y][x] == '#') {
                    count++;
                }
            }
        }
        return count;
    }

    private static char[][] readFromFile() {
        char[][] grid = new char[GRID_SIZE][GRID_SIZE];
        try (BufferedReader br = new BufferedReader(new FileReader("src/main/resources/day18.txt"))) {
             String line;
             int y = 0;
             while ((line = br.readLine()) != null) {
                 for (int x = 0; x < line.length(); x++) {
                     grid[y][x] = line.charAt(x);
                 }
                 y++;
             }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return grid;
    }
}
