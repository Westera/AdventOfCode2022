package aoc2022.solutions;

import aoc2022.util.DailyInputReader;
import aoc2022.util.Day;

import java.io.IOException;

public class Day02 extends Day {

    public Day02() {

    }

    public Day02(String input) {
        super(input);
    }

    public static void main(String[] args) {
        new Day02("src/main/resources/Day02/input");
    }

    @Override
    public String part1(String path) {
        try {
            int result = DailyInputReader.getInputFileToLines(path).mapToInt(row -> calculateScoreV1(row.charAt(0), row.charAt(2))).sum();
            return String.format("Total Score: %d", result);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public String part2(String path) {
        try {
            int result = DailyInputReader.getInputFileToLines(path).mapToInt(row -> calculateScoreV2(row.charAt(0), row.charAt(2))).sum();
            return String.format("Total Score: %d", result);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private int calculateScoreV1(char them, char you) {
        int resultPoint = (((you - 'X' - them + 'A' + 3) % 3) + 1) % 3 * 3;
        int choicePoint = you - 'X' + 1;
        return resultPoint + choicePoint;
    }

    private int calculateScoreV2(char them, char wantedResult) {
        int resultPoint = (wantedResult - 'X') * 3;
        int choicePoint = (them + wantedResult - 'X') % 3 + 1;
        return resultPoint + choicePoint;
    }
}
