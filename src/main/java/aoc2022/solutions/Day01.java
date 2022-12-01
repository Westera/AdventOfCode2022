package aoc2022.solutions;

import aoc2022.util.DailyInputReader;
import aoc2022.util.Day;

import java.io.IOException;
import java.util.*;
import java.util.stream.IntStream;

public class Day01 extends Day {

    public Day01() {

    }
    public static void main(String[] args){
        new Day01("src/main/resources/Day01/input");
    }
    public Day01(String input) {
        super(input);
    }

    @Override
    public String part1(String path) {
        int result = getCaloriesForEachElf(path).max().orElse(-1);

        return String.format("Max total weight is: %d", result);
    }

    @Override
    public String part2(String path) {
        int result = getCaloriesForEachElf(path).
                boxed().sorted(Comparator.reverseOrder()).limit(3).reduce(Integer::sum).orElse(-1);

        return String.format("Max total weight is: %d", result);
    }

    private IntStream getCaloriesForEachElf(String path) {
        try {
            return Arrays.stream(DailyInputReader.getInputAsString(path).split("\n\n")).mapToInt(elf ->
                    Arrays.stream(elf.split("\n")).map(String::trim).mapToInt(Integer::parseInt).sum());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
