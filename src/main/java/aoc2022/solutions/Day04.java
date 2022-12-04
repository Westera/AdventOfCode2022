package aoc2022.solutions;

import aoc2022.util.DailyInputReader;
import aoc2022.util.Day;

import java.io.IOException;
import java.util.List;

interface OverlapFinder {
    boolean findOverlap(List<Integer> row);
}

public class Day04 extends Day {

    public Day04() {

    }

    public Day04(String input) {
        super(input);
    }

    public static void main(String[] args) {
        new Day04("src/main/resources/Day04/input");
    }

    @Override
    public String part1(String path) {
        OverlapFinder findFullOverlap = this::isEitherFullyContained;
        return String.format("Number of covered: %d", getOverlap(path, findFullOverlap));
    }

    @Override
    public String part2(String path) {
        OverlapFinder findAnyOverlap = this::isAnyOverlap;
        return String.format("Number of covered: %d", getOverlap(path, findAnyOverlap));
    }

    private int getOverlap(String path, OverlapFinder finder) {
        try {
            return DailyInputReader.getInputFileToLines(path).
                    map(row -> DailyInputReader.getInputSplitOnX(row, "[-,]").
                            map(Integer::parseInt).toList()).mapToInt(row -> finder.findOverlap(row) ? 1 : 0).sum();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private boolean isEitherFullyContained(List<Integer> row) {
        return (row.get(0) >= row.get(2) && row.get(1) <= row.get(3)) ||
                (row.get(2) >= row.get(0) && row.get(3) <= row.get(1));
    }

    private boolean isAnyOverlap(List<Integer> row) {
        return (row.get(0) >= row.get(2) && row.get(0) <= row.get(3)) ||
                (row.get(1) >= row.get(2) && row.get(1) <= row.get(3)) ||
                (row.get(2) >= row.get(0) && row.get(2) <= row.get(1)) ||
                (row.get(3) >= row.get(0) && row.get(3) <= row.get(1));
    }
}
