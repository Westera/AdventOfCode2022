package aoc2022.solutions;

import aoc2022.util.DailyInputReader;
import aoc2022.util.Day;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

public class Day08 extends Day {

    public Day08() {

    }

    public Day08(String input) {
        super(input);
    }

    public static void main(String[] args) {
        new Day08("src/main/resources/Day08/input");
    }

    @Override
    public String part1(String path) {
        try {
            List<String> rows = DailyInputReader.getInputFileToLines(path).toList();
            int[][] forest = getForest(rows);
            int numberOfSeen = 0;
            for (int i = 0; i < forest.length; i++) {
                for (int j = 0; j < forest[i].length; j++) {
                    for (Direction direction : Direction.values()) {
                        if (isVisibleInDirection(direction, forest, j, i)) {
                            numberOfSeen++;
                            break;
                        }
                    }
                }
            }
            return String.format("Amount of Visible Trees: %d", numberOfSeen);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public String part2(String path) {
        try {
            List<String> rows = DailyInputReader.getInputFileToLines(path).toList();
            int[][] forest = getForest(rows);
            int topScore = 1;
            for (int i = 0; i < forest.length; i++) {
                for (int j = 0; j < forest[i].length; j++) {
                    int score = 1;
                    for (Direction direction : Direction.values()) {
                        score *= scorePointInDirection(direction, forest, j, i);
                    }
                    topScore = Math.max(topScore, score);
                }
            }
            return String.format("Best Scenic Score: %d", topScore);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private int[][] getForest(List<String> rows) {
        int[][] forest = new int[rows.size()][];
        Arrays.setAll(forest, row -> new int[rows.get(0).trim().length()]);
        for (int i = 0; i < rows.size(); i++) {
            String row = rows.get(i);
            for (int j = 0; j < row.trim().length(); j++) {
                forest[i][j] = Character.getNumericValue(row.charAt(j));
            }
        }
        return forest;
    }

    private boolean isVisibleInDirection(Direction direction, int[][] forest, int xCoordinate, int yCoordinate) {
        boolean canBeSeen = true;
        int currentTree = forest[yCoordinate][xCoordinate];
        for (int x = xCoordinate, y = yCoordinate;
             x > 0 && x < forest[yCoordinate].length - 1 && y > 0 && y < forest.length - 1;
             x += direction.deltaX, y += direction.deltaY) {

            int tree = forest[y + direction.deltaY][x + direction.deltaX];
            if (currentTree <= tree) {
                canBeSeen = false;
                break;
            }
        }
        return canBeSeen;
    }

    private int scorePointInDirection(Direction direction, int[][] forest, int xCoordinate, int yCoordinate) {
        int score = 0;
        int currentTree = forest[yCoordinate][xCoordinate];
        for (int x = xCoordinate, y = yCoordinate;
             x > 0 && x < forest[yCoordinate].length - 1 && y > 0 && y < forest.length - 1;
             x += direction.deltaX, y += direction.deltaY) {

            score++;
            if (currentTree <= forest[y + direction.deltaY][x + direction.deltaX]) {
                break;
            }
        }
        return score;
    }

    private enum Direction {
        WEST(-1, 0), EAST(1, 0), NORTH(0, -1), SOUTH(0, 1);

        final int deltaX;
        final int deltaY;

        Direction(int deltaX, int deltaY) {
            this.deltaX = deltaX;
            this.deltaY = deltaY;
        }
    }
}
