package no.sanderolin.day07;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SomeAssemblyRequired01 {

    private static HashMap<String, Instruction> wires;
    private static final Pattern BINARY_PATTERN = Pattern.compile("(\\w+) (AND|OR|LSHIFT|RSHIFT) (\\w+) -> (\\w+)");
    private static final Pattern UNARY_PATTERN = Pattern.compile("NOT (\\w+) -> (\\w+)");
    private static final Pattern ASSIGNMENT_PATTERN = Pattern.compile("(\\w+|\\d+) -> (\\w+)");

    public static void solve() {
        wires = new HashMap<>();
        readFromFile();
        System.out.println(findWireValue("a"));
    }

    private static int findWireValue(String key) {
        if (key.matches("\\d+")) return Integer.parseInt(key);

        Instruction instruction = wires.get(key);
        if (instruction.getValue() != -1) return instruction.getValue();

        int result;
        switch (instruction.getOperation()) {
            case AND -> result = findWireValue(instruction.getWire1()) & findWireValue(instruction.getWire2());
            case OR -> result = findWireValue(instruction.getWire1()) | findWireValue(instruction.getWire2());
            case LSHIFT -> result = findWireValue(instruction.getWire1()) << findWireValue(instruction.getWire2());
            case RSHIFT -> result = findWireValue(instruction.getWire1()) >> findWireValue(instruction.getWire2());
            case COMPLEMENT -> result = ~findWireValue(instruction.getWire1()) & 0xFFFF;
            default -> result = findWireValue(instruction.getWire1());
        }

        instruction.setValue(result);
        return result;
    }

    private static void readFromFile() {
        try (BufferedReader br = new BufferedReader(new FileReader("src/main/resources/day07.txt"))) {
            String line;
            while ((line = br.readLine()) != null) {
                Matcher matcher;
                if ((matcher = BINARY_PATTERN.matcher(line)).matches()) {
                    wires.put(matcher.group(4),
                            new Instruction(matcher.group(1),
                                    Operation.valueOf(matcher.group(2)),
                                    matcher.group(3),
                                    -1));

                } else if ((matcher = UNARY_PATTERN.matcher(line)).matches() || (matcher = ASSIGNMENT_PATTERN.matcher(line)).matches()) {
                    Operation operation = (matcher.pattern() == UNARY_PATTERN) ? Operation.COMPLEMENT : Operation.ASSIGN;
                    wires.put(matcher.group(2), new Instruction(matcher.group(1), operation, null, -1));
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private enum Operation { AND, OR, LSHIFT, RSHIFT, COMPLEMENT, ASSIGN }

    @Data
    @AllArgsConstructor
    public static class Instruction {
        private final String wire1;
        private final Operation operation;
        private final String wire2;
        private int value;
    }
}
