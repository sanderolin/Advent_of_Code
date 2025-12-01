package no.sanderolin.day16;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AuntSue01 {

    private static final AuntSue CORRECT_AUNT_SUE = new AuntSue(
            0,3, 7, 2, 3, 0,
            0, 5, 3, 2, 1
    );

    public static void solve() {
        List<AuntSue> auntSues = readFromFile();
        int idOfCorrectAuntSue = findCorrectAuntSue(auntSues);
        System.out.println(idOfCorrectAuntSue);
    }

    private static int findCorrectAuntSue(List<AuntSue> auntSues) {
        return auntSues.stream()
            .filter(AuntSue01::matchesCorrectAuntSue)
            .map(AuntSue::id)
            .findFirst()
            .orElse(0);
    }

    private static boolean matchesCorrectAuntSue(AuntSue sue) {
        return (sue.children() == -1 || sue.children() == CORRECT_AUNT_SUE.children()) &&
                (sue.cats() == -1 || sue.cats() == CORRECT_AUNT_SUE.cats()) &&
                (sue.samoyeds() == -1 || sue.samoyeds() == CORRECT_AUNT_SUE.samoyeds()) &&
                (sue.pomeranians() == -1 || sue.pomeranians() == CORRECT_AUNT_SUE.pomeranians()) &&
                (sue.akitas() == -1 || sue.akitas() == CORRECT_AUNT_SUE.akitas()) &&
                (sue.vizslas() == -1 || sue.vizslas() == CORRECT_AUNT_SUE.vizslas()) &&
                (sue.goldfish() == -1 || sue.goldfish() == CORRECT_AUNT_SUE.goldfish()) &&
                (sue.trees() == -1 || sue.trees() == CORRECT_AUNT_SUE.trees()) &&
                (sue.cars() == -1 || sue.cars() == CORRECT_AUNT_SUE.cars()) &&
                (sue.perfumes() == -1 || sue.perfumes() == CORRECT_AUNT_SUE.perfumes());
    }

    private static List<AuntSue> readFromFile() {
        List<AuntSue> auntSues = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader("src/main/resources/day16.txt"))) {
            String line;
            int id = 1;
            while ((line = br.readLine()) != null) {
                AuntSue auntSue = parseAuntSue(id, line);
                auntSues.add(auntSue);
                id++;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return auntSues;
    }

    private static AuntSue parseAuntSue(int id, String line) {
        Map<String, Integer> properties = new HashMap<>();
        line = line.replaceFirst("Sue (\\d+):", "");
        String[] propertyPairs = line.split(", ");
        for (String propertyPair : propertyPairs) {
            String[] keyValue = propertyPair.split(": ");
            String key = keyValue[0].trim();
            int value = Integer.parseInt(keyValue[1].trim());
            properties.put(key, value);
        }
        return new AuntSue(
                id,
                properties.getOrDefault("children", -1),
                properties.getOrDefault("cats", -1),
                properties.getOrDefault("samoyeds", -1),
                properties.getOrDefault("pomeranians", -1),
                properties.getOrDefault("akitas", -1),
                properties.getOrDefault("vizslas", -1),
                properties.getOrDefault("goldfish", -1),
                properties.getOrDefault("trees", -1),
                properties.getOrDefault("cars", -1),
                properties.getOrDefault("perfumes", -1)
        );
    }

    private record AuntSue (int id, int children, int cats, int samoyeds, int pomeranians, int akitas,
                            int vizslas, int goldfish, int trees, int cars, int perfumes) {}
}
