package no.sanderolin.day14;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ReindeerOlympics01 {

    private static final int RACE_DURATION = 2503;

    public static void solve() {
        List<Reindeer> reindeer = readFromFile();
        int winningDistance = findWinningReindeerDistance(reindeer);
        System.out.println(winningDistance);
    }

    private static int findWinningReindeerDistance(List<Reindeer> reindeer) {
        int winningReindeerDistance = 0;
        for (Reindeer rd : reindeer) {
            int cycle = rd.flyTime() + rd.restTime();
            int numberOfCycles = RACE_DURATION / cycle;
            int remainingTime = Math.min(RACE_DURATION % cycle, rd.flyTime());
            int distance = (rd.speed() * rd.flyTime() * numberOfCycles) + (rd.speed() * remainingTime);
            if (distance > winningReindeerDistance) winningReindeerDistance = distance;
        }
        return winningReindeerDistance;
    }

    private static List<Reindeer> readFromFile() {
        List<Reindeer> reindeer = new ArrayList<>();
        Pattern pattern = Pattern.compile("(\\w+) can fly (\\d+) km/s for (\\d+) seconds, but then must rest for (\\d+) seconds.");
        try (BufferedReader br = new BufferedReader(new FileReader("src/main/resources/day14.txt"))) {
            br.lines()
                .map(pattern::matcher)
                .filter(Matcher::matches)
                .forEach(matcher ->
                        reindeer.add(new Reindeer(matcher.group(1),
                        Integer.parseInt(matcher.group(2)),
                        Integer.parseInt(matcher.group(3)),
                        Integer.parseInt(matcher.group(4)))));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return reindeer;
    }

    private record Reindeer(String name, int speed, int flyTime, int restTime) {}
}
