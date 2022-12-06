package aoc2022.solutions;

import aoc2022.util.DailyInputReader;
import aoc2022.util.Day;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Day06 extends Day {

    public Day06() {

    }

    public Day06(String input) {
        super(input);
    }

    public static void main(String[] args) {
        new Day06("src/main/resources/Day06/input");
    }

    @Override
    public String part1(String path) {
        return String.format("Processed Characters: %d", getIndexOfFirstXUniqueCharacters(path, 4));
    }

    @Override
    public String part2(String path) {
        return String.format("Processed Characters: %d", getIndexOfFirstXUniqueCharacters(path, 14));
    }

    private int getIndexOfFirstXUniqueCharacters(String path, int numberOfUniqueCharacters) {
        try {
            String inputString = DailyInputReader.getInputAsString(path);
            List<Character> characters = new ArrayList<>();
            DailyInputReader.getInputCharByChar(inputString).
                    takeWhile(i -> characters.size() != numberOfUniqueCharacters).forEachOrdered(c -> {
                        while (characters.contains(c)) {
                            characters.remove(0);
                        }
                        characters.add(c);
                    });
            String uniqueString = characters.stream().map(String::valueOf)
                    .collect(Collectors.joining());
            return inputString.indexOf(uniqueString) + numberOfUniqueCharacters;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
