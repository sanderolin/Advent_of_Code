package no.sanderolin.day14;

import lombok.Data;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ReindeerOlympics02 {

    private static final int RACE_DURATION = 2503;

    public static void solve() {
        List<Reindeer> reindeer = readFromFile();
        int pointsOfWinningReindeer = findPointsOfWinningReindeer(reindeer);
        System.out.println(pointsOfWinningReindeer);
    }

    private static int findPointsOfWinningReindeer(List<Reindeer> reindeer) {
        for (int currentSecond = 0; currentSecond < RACE_DURATION; currentSecond++) {
            for (Reindeer rd : reindeer) {
                rd.move(currentSecond);
            }
            givePointToReindeerInLead(reindeer);
        }
        return reindeer.stream().mapToInt(Reindeer::getPoints).max().orElse(0);
    }

    private static void givePointToReindeerInLead(List<Reindeer> reindeer) {
        int distanceOfReindeerInLead = reindeer.stream()
                .mapToInt(Reindeer::getDistanceTraveled)
                .max().orElse(0);

        reindeer.stream()
                .filter(rd -> rd.getDistanceTraveled() == distanceOfReindeerInLead)
                .forEach(Reindeer::addPoint);
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

    @Data
    private static class Reindeer {
        private final String name;
        private final int speed;
        private final int flyTime;
        private final int restTime;
        private final int cycle;
        private int distanceTraveled;
        private int points;

        public Reindeer(String name, int speed, int flyTime, int restTime) {
            this.name = name;
            this.speed = speed;
            this.flyTime = flyTime;
            this.restTime = restTime;
            this.cycle = flyTime + restTime;
        }

        public void move (int currentSecond) {
            if (currentSecond % cycle < flyTime) distanceTraveled += speed;
        }

        public void addPoint () {
            points++;
        }
    }
}
