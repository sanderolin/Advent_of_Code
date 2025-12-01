package no.sanderolin.day12;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.BufferedReader;
import java.io.FileReader;
public class JSAbacusFrameworkIO02 {

    public static void solve() {
        String jsonDocument = readFromFile();
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            JsonNode rootNode = objectMapper.readTree(jsonDocument);
            int sum = calculateSum(rootNode);
            System.out.println(sum);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static int calculateSum(JsonNode node) {
        if (node.isObject()) {
            for (JsonNode child : node) {
                if (child.isTextual() && child.asText().equals("red")) {
                    return 0;
                }
            }
        }
        if (node.isNumber()) {
            return node.asInt();
        } else if (node.isArray() || node.isObject()) {
            int sum = 0;
            for (JsonNode child : node) {
                sum += calculateSum(child);
            }
            return sum;
        }
        return 0;
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

