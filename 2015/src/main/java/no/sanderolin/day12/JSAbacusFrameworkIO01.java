package no.sanderolin.day12;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class JSAbacusFrameworkIO01 {

    public static void solve() {
        String line = readFromFile();
        System.out.println(countNumbersInDocument(line));
    }

    private static int countNumbersInDocument(String line) {
        Pattern pattern = Pattern.compile("-?\\d+");
        Matcher matcher = pattern.matcher(line);
        int sum = 0;
        while (matcher.find()) {
            sum += Integer.parseInt(matcher.group());
        }
        return sum;
    }

    private static String readFromFile() {
        String line = "";
        try (BufferedReader br = new BufferedReader(new FileReader("src/main/resources/day12.txt"))) {
            line = br.readLine();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return line;
    }
}
