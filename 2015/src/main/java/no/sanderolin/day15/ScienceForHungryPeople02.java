package no.sanderolin.day15;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ScienceForHungryPeople02 {

    private static final int TEASPOONS = 100;

    public static void solve() {
        List<Ingredient> ingredients = readFromFile();
        long scoreOfHighestScoringCookie = findHighestScore(ingredients, new int[ingredients.size()], 0, TEASPOONS);
        System.out.println(scoreOfHighestScoringCookie);
    }

    private static long findHighestScore(List<Ingredient> ingredients, int[] teaspoons, int index, int remainingTeaspoons) {
        if (index >= ingredients.size() - 1) {
            teaspoons[index] = remainingTeaspoons;
            return calculateScore(ingredients, teaspoons);
        }
        long highestScore = 0;
        for (int i = 0; i <= remainingTeaspoons; i++) {
            teaspoons[index] = i;
            highestScore = Math.max(highestScore, findHighestScore(ingredients, teaspoons, index + 1, remainingTeaspoons - i));
        }
        return highestScore;
    }

    private static long calculateScore(List<Ingredient> ingredients, int[] teaspoons) {
        int capacity = 0, durability = 0, flavor = 0, texture = 0, calories = 0;
        for (int i = 0; i < ingredients.size(); i++) {
            capacity += ingredients.get(i).capacity() * teaspoons[i];
            durability += ingredients.get(i).durability() * teaspoons[i];
            flavor += ingredients.get(i).flavor() * teaspoons[i];
            texture += ingredients.get(i).texture() * teaspoons[i];
            calories += ingredients.get(i).calories() * teaspoons[i];
        }
        capacity = Math.max(0, capacity);
        durability = Math.max(0, durability);
        flavor = Math.max(0, flavor);
        texture = Math.max(0, texture);
        if (calories != 500) return 0;
        return (long) capacity * durability * flavor * texture;
    }

    private static List<Ingredient> readFromFile() {
        List<Ingredient> ingredients = new ArrayList<>();
        Pattern pattern = Pattern.compile("(\\w+): capacity (-?\\d+), durability (-?\\d+), flavor (-?\\d+), texture (-?\\d+), calories (-?\\d+)");
        try (BufferedReader br = new BufferedReader(new FileReader("src/main/resources/day15.txt"))) {
            br.lines()
                .map(pattern::matcher)
                .filter(Matcher::matches)
                .forEach(matcher ->
                        ingredients.add(new Ingredient(matcher.group(1),
                        Integer.parseInt(matcher.group(2)),
                        Integer.parseInt(matcher.group(3)),
                        Integer.parseInt(matcher.group(4)),
                        Integer.parseInt(matcher.group(5)),
                        Integer.parseInt(matcher.group(6))
                )));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ingredients;
    }

    private record Ingredient(String name, int capacity,  int durability, int flavor, int texture, int calories) {}
}
