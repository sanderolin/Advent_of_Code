package no.sanderolin.day09;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class AllInASingleNight02 {

    private static ArrayList<Edge> allEdges;
    private static ArrayList<ArrayList<Edge>> allPossiblePaths;
    private static Set<String> nodes;

    public static void solve() {
        readFromFile();
        findAllPaths();
        System.out.println(findLowestCost());
    }

    private static void findAllPaths() {
        for (String startNode : nodes) {
            ArrayList<Edge> currentPath = new ArrayList<>();
            Set<String> visited = new HashSet<>();
            visited.add(startNode);
            findPathsFromStartNode(startNode, visited, currentPath);
        }
    }

    private static void findPathsFromStartNode(String currentNode, Set<String> visited, ArrayList<Edge> currentPath) {
        if (visited.size() == nodes.size()) {
            allPossiblePaths.add(new ArrayList<>(currentPath));
            return;
        }

        for (Edge edge : allEdges) {
            String nextNode;
            if (edge.from().equals(currentNode) && !visited.contains(edge.to())) nextNode = edge.to();
            else if (edge.to().equals(currentNode) && !visited.contains(edge.from())) nextNode = edge.from();
            else continue;

            visited.add(nextNode);
            currentPath.add(edge);
            findPathsFromStartNode(nextNode, visited, currentPath);
            currentPath.remove(currentPath.size() - 1);
            visited.remove(nextNode);
        }
    }

    private static int findLowestCost() {
        int highestCost = 0;
        for (ArrayList<Edge> path : allPossiblePaths) {
            int cost = 0;
            for (Edge edge : path) {
                cost += edge.weight();
            }
            if (cost > highestCost) highestCost = cost;
        }
        return highestCost;
    }

    private static void readFromFile() {
        allEdges = new ArrayList<>();
        nodes = new HashSet<>();
        allPossiblePaths = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader("src/main/resources/day09.txt"))) {
            String line;
            while ((line = br.readLine()) != null) {
                Pattern pattern = Pattern.compile("(\\w+) to (\\w+) = (\\d+)");
                Matcher matcher = pattern.matcher(line);
                if (matcher.matches()) {
                    nodes.add(matcher.group(1));
                    nodes.add(matcher.group(2));
                    allEdges.add(new Edge(matcher.group(1), matcher.group(2), Integer.parseInt(matcher.group(3))));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private record Edge(String from, String to, int weight) {}
}
