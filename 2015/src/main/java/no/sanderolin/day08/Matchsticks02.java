package no.sanderolin.day08;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;

public class Matchsticks02 {

    public static void solve() {
        ArrayList<String> file = readFromFile();
        System.out.println(parseFile(file));
    }

    private static int parseFile(ArrayList<String> file) {
        int numberStringLiteral = 0;
        int numberNewEncoded = 0;
        for (String line : file) {
            numberStringLiteral += line.length();
            line = line.replaceAll("\\\\\\\\", "????");
            line = line.replaceAll("\\\\\"", "????");
            line = line.replaceAll("\\\\x\\w{2}", "?????");
            numberNewEncoded += (line.length() + 4);
        }
        return numberNewEncoded - numberStringLiteral;
    }

    private static ArrayList<String> readFromFile() {
        ArrayList<String> result = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader("src/main/resources/day08.txt"))) {
            String line;
            while ((line = br.readLine()) != null) {
                result.add(line);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }
}

