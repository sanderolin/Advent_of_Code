package no.sanderolin.day17;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;

public class NoSuchThingAsTooMuch01 {

    private static final int LITERS_OF_EGGNOG = 150;

    public static void solve() {
        List<Container> containers = readFromFile();
        Set<Set<Container>> combinations = findAllCombinations(containers);
        System.out.println(combinations.size());
    }

    private static Set<Set<Container>> findAllCombinations(List<Container> containers) {
        Set<Set<Container>> combinations = new HashSet<>();
        findCombinationsRecursive(containers, LITERS_OF_EGGNOG, 0, new HashSet<>(), combinations);
        return combinations;
    }

    private static void findCombinationsRecursive(
            List<Container> containers,
            int targetVolume,
            int startIndex,
            Set<Container> currentCombination,
            Set<Set<Container>> allCombinations
    ) {
        if (targetVolume == 0) { // Return combination when enough of that sweet eggnog is stored
            allCombinations.add(new HashSet<>(currentCombination));
            return;
        }

        // If over shoot, or not enough containers left, return
        if (targetVolume < 0 || startIndex >= containers.size()) return;


        for (int i = startIndex; i < containers.size(); i++) {
            Container container = containers.get(i);
            currentCombination.add(container);
            findCombinationsRecursive(containers, targetVolume - container.volume(), i + 1, currentCombination, allCombinations);
            currentCombination.remove(container);
        }
    }

    private static List<Container> readFromFile() {
        List<Container> list = new ArrayList<>();
        AtomicInteger counter = new AtomicInteger(0);
        try (BufferedReader br = new BufferedReader(new FileReader("src/main/resources/day17.txt"))) {
            br.lines()
                .map(Integer::parseInt)
                .forEach(e ->
                        list.add(new Container(counter.getAndIncrement(), e))
                );
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    private record Container(int id, int volume) {}
}
