package aoc2022.solutions;

import aoc2022.util.DailyInputReader;
import aoc2022.util.Day;

import java.io.IOException;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

public class Day03 extends Day {

    public Day03() {

    }

    public Day03(String input) {
        super(input);
    }

    public static void main(String[] args) {
        new Day03("src/main/resources/Day03/input");
    }

    @Override
    public String part1(String path) {
        try {
            int total = DailyInputReader.getInputFileToLines(path).
                    map(row -> new String[]{row.substring(0, row.length() / 2),
                            row.substring(row.length() / 2)}).
                    mapToInt(backpack -> calcPrio(DailyInputReader.getInputCharByChar(backpack[0]).
                            filter(c -> backpack[1].contains(c.toString())).findFirst().orElse('.'))).sum();
            return String.format("Total Priority: %d", total);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public String part2(String path) {
        final int groupSize = 3;
        final AtomicInteger counter = new AtomicInteger();

        try {
            int total = DailyInputReader.getInputFileToLines(path).
                    collect(Collectors.groupingBy(it -> counter.getAndIncrement() / groupSize)).values().stream().
                    mapToInt(group -> calcPrio(DailyInputReader.getInputCharByChar(group.get(0)).
                            filter(c -> group.get(1).contains(c.toString()) && group.get(2).contains(c.toString())).
                            findFirst().orElse('.'))).sum();
            return String.format("Total Priority: %d", total);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private int calcPrio(char c) {
        int small = c - 'a' + 1;
        int big = c - 'A' + 27;
        return small < 0 ? big : small;
    }
}