package aoc2022.solutions;

import aoc2022.util.DailyInputReader;
import aoc2022.util.Day;

import java.io.IOException;
import java.util.*;

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
        int result = getSuppliesListForEachElf(path).stream().map(elf ->
                elf.stream().map(Integer::parseInt).reduce(0, Integer::sum)).
                max(Integer::compare).orElse(-1);

        return String.format("Max total weight is: %d", result);
    }

    @Override
    public String part2(String path) {
        int result = getSuppliesListForEachElf(path).stream().map(elf ->
                elf.stream().map(Integer::parseInt).reduce(0, Integer::sum)).
                sorted(Comparator.reverseOrder()).limit(3).reduce(Integer::sum).orElse(-1);

        return String.format("Max total weight is: %d", result);
    }

    private List<List<String>> getSuppliesListForEachElf(String path){
        List<List<String>> initial = new ArrayList<>();
        initial.add(new ArrayList<>());

        List<List<String>> suppliesForEachElf = null;
        try {
            suppliesForEachElf = DailyInputReader.getInputFileToLines(path).
                    reduce(initial, (subtotal, line) -> {
                        if (line.isEmpty()) {
                            subtotal.add(new ArrayList<>());
                        } else {
                            subtotal.get(subtotal.size() - 1).add(line);
                        }
                        return subtotal;
                    }, (list1, list2) -> Collections.emptyList());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return suppliesForEachElf;
    }
}
