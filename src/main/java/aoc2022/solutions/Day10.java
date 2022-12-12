package aoc2022.solutions;

import aoc2022.util.DailyInputReader;
import aoc2022.util.Day;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

public class Day10 extends Day {

    public Day10() {

    }

    public Day10(String input) {
        super(input);
    }

    public static void main(String[] args) {
        new Day10("src/main/resources/Day10/input");
    }

    @Override
    public String part1(String path) {
        try {
            AtomicInteger numberOfCycles = new AtomicInteger();
            List<Integer> interesting = new ArrayList<>(Arrays.asList(20, 60, 100, 140, 180, 220));
            AtomicInteger signalStrength = new AtomicInteger();
            AtomicInteger x = new AtomicInteger(1);
            DailyInputReader.getInputFileToLines(path).map(row -> row.split(" ")).takeWhile(strings -> !interesting.isEmpty()).forEachOrdered(row -> {
                Operation op = Operation.valueOf(row[0].toUpperCase());
                int currentCycle = numberOfCycles.addAndGet(op.cycles);
                if (currentCycle >= interesting.get(0)) {
                    signalStrength.addAndGet(x.get() * interesting.get(0));
                    interesting.remove(0);
                }
                if (op == Operation.ADDX) {
                    x.addAndGet(Integer.parseInt(row[1]));
                }
            });
            return String.format("Total signal strength: %d", signalStrength.get());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public String part2(String path) {
        try {
            StringBuilder crtRows = new StringBuilder();
            AtomicInteger numberOfCycles = new AtomicInteger();
            AtomicInteger x = new AtomicInteger(1);
            DailyInputReader.getInputFileToLines(path).map(row -> row.split(" ")).forEachOrdered(row -> {
                Operation op = Operation.valueOf(row[0].toUpperCase());
                for (int i = 0; i < op.cycles; i++) {
                    int currentCycle = numberOfCycles.getAndIncrement();
                    if ((currentCycle % 40) >= x.get() - 1 && (currentCycle % 40) <= x.get() + 1) {
                        crtRows.append("#");
                    } else {
                        crtRows.append(".");
                    }
                    if (currentCycle % 40 == 39) {
                        crtRows.append("\n");
                    }
                }
                if (op == Operation.ADDX) {
                    x.addAndGet(Integer.parseInt(row[1]));
                }
            });
            return String.format("CRT Outprint:\n%s", crtRows);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private enum Operation {
        NOOP(1), ADDX(2);

        private final int cycles;

        Operation(int cycles) {
            this.cycles = cycles;
        }
    }
}
