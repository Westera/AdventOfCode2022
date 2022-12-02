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
    public static void main(String[] args){
        new Day02("src/main/resources/Day02/input");
    }

    @Override
    public String part1(String path) {
        try {
            int result = DailyInputReader.getInputFileToLines(path).mapToInt(row -> {
                String[] choices = row.split(" ");
                return calculateScoreV1(choices[0], choices[1]);
            }).sum();

            return String.format("Total Score: %d", result);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public String part2(String path) {
        try {
            int result = DailyInputReader.getInputFileToLines(path).mapToInt(row -> {
                String[] choices = row.split(" ");
                return calculateScoreV2(choices[0], choices[1]);
            }).sum();

            return String.format("Total Score: %d", result);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private int calculateScoreV1(String them, String you) {
        int resultPoint = (((you.charAt(0) - 87 - (them.charAt(0) - 64) + 3) % 3) + 1) % 3 * 3;
        int choicePoint = you.charAt(0) - 87;
        return resultPoint + choicePoint;
    }

    private int calculateScoreV2(String them, String wantedResult) {
        int resultPoint = (wantedResult.charAt(0) - 88) * 3;
        int choicePoint = (them.charAt(0) + wantedResult.charAt(0) - 89 + 1) % 3 + 1;
        return resultPoint + choicePoint;
    }
}
