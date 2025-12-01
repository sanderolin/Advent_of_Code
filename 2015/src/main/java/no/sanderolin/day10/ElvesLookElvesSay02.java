package no.sanderolin.day10;

import java.io.BufferedReader;
import java.io.FileReader;

public class ElvesLookElvesSay02 {

    public static void solve() {
        String line = readFromFile();
        int numberOfRuns = 50;
        for (int i = 0; i < numberOfRuns; i++) {
            line = lookAndSay(line);
        }
        System.out.println(line.length());
    }

    private static String lookAndSay(String input) {
        StringBuilder result = new StringBuilder();
        int i = 0;
        while (i < input.length()) {
            char currentInteger = input.charAt(i);
            int count = 1;
            while (i + 1 < input.length() && input.charAt(i + 1) == currentInteger) {
                count++;
                i++;
            }
            result.append(count).append(currentInteger);
            i++;
        }
        return result.toString();
    }

    private static String readFromFile() {
        String line = "";
        try (BufferedReader bf = new BufferedReader(new FileReader("src/main/resources/day10.txt"))) {
            line = bf.readLine();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return line;
    }
}
