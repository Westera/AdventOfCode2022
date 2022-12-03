package aoc2022.util;

public abstract class Day {

    public Day() {

    }

    public Day(String input) {
        printSolutions(input);
    }

    public abstract String part1(String input);

    public abstract String part2(String input);

    public void printSolutions(String input) {
        System.out.printf("Todays solutions are:\n" +
                "Part 1: %s\n" +
                "Part 2: %s", part1(input), part2(input));
    }
}
