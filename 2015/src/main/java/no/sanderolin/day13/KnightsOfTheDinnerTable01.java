package no.sanderolin.day13;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class KnightsOfTheDinnerTable01 {

    private static Map<Pair, Integer> relations;
    private static List<String> people;

    public static void solve() {
        readFromFile();
        List<List<String>> everyPermutation = new ArrayList<>();
        getPermutations(0, everyPermutation);
        System.out.println(findBestHappinessScore(everyPermutation));
    }

    private static int findBestHappinessScore(List<List<String>> everyPermutations) {
        int highestScore = 0;
        for (List<String> permutation : everyPermutations) {
            int currentScore = 0;
            for (int i = 0; i < permutation.size(); i++) {
                String leftNeighbor = permutation.get((i - 1 + permutation.size()) % permutation.size());;
                String person = permutation.get(i);
                String rightNeighbor = permutation.get((i + 1) % permutation.size());
                currentScore += relations.get(new Pair(person, leftNeighbor)) + relations.get(new Pair(person, rightNeighbor));
            }
            if (currentScore > highestScore) highestScore = currentScore;
        }
        return highestScore;
    }

    private static void getPermutations(int index, List<List<String>> everyPermutation) {
        if (index == people.size() - 1) {
            everyPermutation.add(new ArrayList<>(people));
            return;
        }
        for (int i = index; i < people.size(); i++) {
            Collections.swap(people, i, index);
            getPermutations(index + 1, everyPermutation);
            Collections.swap(people, i, index);
        }
    }

    private static void readFromFile() {
        relations = new HashMap<>();
        HashSet<String> set = new HashSet<>();
        Pattern pattern = Pattern.compile("(\\w+) would (gain|lose) (\\d+) happiness units by sitting next to (\\w+).");
        try (BufferedReader br = new BufferedReader(new FileReader("src/main/resources/day13.txt"))) {
            br.lines()
                .map(pattern::matcher)
                .filter(Matcher::matches)
                .forEach(match -> {
                    int happiness = Integer.parseInt(match.group(3));
                    if (match.group(2).equals("lose")) happiness = -happiness;
                    relations.put(new Pair(match.group(1), match.group(4)), happiness);
                    set.addAll(List.of(match.group(1), match.group(4)));
                });
        } catch (Exception e) {
            e.printStackTrace();
        }
        people = new ArrayList<>(set);
    }

    private record Pair(String personA, String personB) {}
}
