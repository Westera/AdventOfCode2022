package aoc2022.solutions;

import aoc2022.util.DailyInputReader;
import aoc2022.util.Day;

import java.io.IOException;
import java.util.*;
import java.util.stream.IntStream;

public class Day11 extends Day {

    public Day11() {

    }

    public Day11(String input) {
        super(input);
    }

    public static void main(String[] args) {
        new Day11("src/main/resources/Day11/input");
    }

    @Override
    public String part1(String path) {
        List<Monkey> monkeys = getMonkeys(path);

        IntStream.range(0, 20).forEach(round -> {
            monkeys.stream().forEachOrdered(monkey -> {
                Iterator<Long> iter = monkey.items.iterator();
                while (iter.hasNext()) {
                    long inspectedItem = monkey.abilities.inspect(iter.next()) / 3;
                    int monkeyIndex = monkey.abilities.test(inspectedItem);
                    monkeys.get(monkeyIndex).items.add(inspectedItem);
                    iter.remove();
                }
            });
        });

        long total = monkeys.stream().map(monkey -> monkey.inspectCounter).sorted(Comparator.reverseOrder()).limit(2).reduce(1L, (integer, integer2) -> integer * integer2);

        return String.format("Monkeys to chase: %d", total);
    }

    @Override
    public String part2(String path) {
        List<Monkey> monkeys = getMonkeys(path);

        int newWorry = monkeys.stream().map(monkey -> monkey.test).reduce(1, (integer, integer2) -> integer * integer2);

        IntStream.range(0, 10000).forEach(round -> {
            monkeys.stream().forEachOrdered(monkey -> {
                Iterator<Long> iter = monkey.items.iterator();
                while (iter.hasNext()) {
                    long inspectedItem = monkey.abilities.inspect(iter.next()) % newWorry;
                    int monkeyIndex = monkey.abilities.test(inspectedItem);
                    monkeys.get(monkeyIndex).items.add(inspectedItem);
                    iter.remove();
                }
            });
        });

        long total = monkeys.stream().map(monkey -> monkey.inspectCounter).sorted(Comparator.reverseOrder()).limit(2).reduce(1L, (integer, integer2) -> integer * integer2);

        return String.format("Monkeys to chase: %d", total);
    }

    private List<Monkey> getMonkeys(String path) {
        try {
            return DailyInputReader.getInputSplitOnX(DailyInputReader.getInputAsString(path), "\n\n").map(group -> DailyInputReader.getInputLineByLine(group).skip(1).map(String::trim).toList()).map(monkey -> {
                List<Long> items = new ArrayList<>(Arrays.stream(monkey.get(0).replace("Starting items: ", "").split(", ")).map(Long::parseLong).toList());
                String operation = monkey.get(1).replace("Operation: new = ", "");
                int test = Integer.parseInt(monkey.get(2).replace("Test: divisible by ", ""));
                int monkeyIfTrue = Integer.parseInt(monkey.get(3).replace("If true: throw to monkey ", ""));
                int monkeyIfFalse = Integer.parseInt(monkey.get(4).replace("If false: throw to monkey ", ""));
                return new Monkey(items, operation.split(" "), test, monkeyIfTrue, monkeyIfFalse);
            }).toList();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private static class Monkey {

        private final List<Long> items;
        private final Abilities abilities;
        private final int test;
        private long inspectCounter;

        public Monkey(List<Long> items, String[] operations, int test, int monkeyIfTrue, int monkeyIfFalse) {
            this.items = items;
            this.test = test;
            this.abilities = new Abilities() {
                @Override
                public int test(long item) {
                    return item % test == 0 ? monkeyIfTrue : monkeyIfFalse;
                }

                @Override
                public long inspect(long item) {
                    inspectCounter++;
                    long a = operations[0].equals("old") ? item : Long.parseLong(operations[0]);
                    long b = operations[2].equals("old") ? item : Long.parseLong(operations[2]);
                    return switch (operations[1]) {
                        case "+" -> a + b;
                        case "-" -> a - b;
                        case "*" -> a * b;
                        case "/" -> a / b;
                        default -> 0;
                    };
                }
            };
        }

        private interface Abilities {
            int test(long item);

            long inspect(long item);
        }
    }
}
