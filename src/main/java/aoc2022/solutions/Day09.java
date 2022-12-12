package aoc2022.solutions;

import aoc2022.util.DailyInputReader;
import aoc2022.util.Day;
import aoc2022.util.Point;

import java.io.IOException;
import java.util.*;

public class Day09 extends Day {

    public Day09() {

    }

    public Day09(String input) {
        super(input);
    }

    public static void main(String[] args) {
        new Day09("src/main/resources/Day09/input");
    }

    @Override
    public String part1(String path) {
        try {
            Set<Point> visited = new HashSet<>();
            Point head = new Point(0, 0), tail = new Point(0, 0);
            visited.add(new Point(0, 0));
            DailyInputReader.getInputFileToLines(path).map(row -> DailyInputReader.getInputSplitOnX(row, " ").
                    toList()).forEachOrdered(row -> {
                int loop = Integer.parseInt(row.get(1));
                for (int i = 0; i < loop; i++) {
                    moveHead(row.get(0), head);
                    moveTail(head, tail);
                    visited.add(new Point(tail.getX(), tail.getY()));
                }
            });
            return String.format("Visited: %d", visited.size());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public String part2(String path) {
        try {
            Set<Point> visited = new HashSet<>();
            Point head = new Point(0, 0);
            List<Point> tails = new ArrayList<>(Arrays.asList(new Point(0, 0),
                    new Point(0, 0), new Point(0, 0), new Point(0, 0), new Point(0, 0),
                    new Point(0, 0), new Point(0, 0), new Point(0, 0), new Point(0, 0)));
            DailyInputReader.getInputFileToLines(path).map(row -> DailyInputReader.getInputSplitOnX(row, " ").
                    toList()).forEachOrdered(row -> {
                int loop = Integer.parseInt(row.get(1));
                for (int i = 0; i < loop; i++) {
                    moveHead(row.get(0), head);
                    moveTail(head, tails.get(0));
                    for (int j = 1; j < tails.size(); j++) {
                        moveTail(tails.get(j - 1), tails.get(j));
                    }

                    visited.add(new Point(tails.get(tails.size() - 1).getX(), tails.get(tails.size() - 1).getY()));
                }
            });
            return String.format("Visited: %d", visited.size());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private void moveHead(String direction, Point head) {
        switch (direction) {
            case "L" -> head.setX(head.getX() - 1);
            case "R" -> head.setX(head.getX() + 1);
            case "D" -> head.setY(head.getY() - 1);
            case "U" -> head.setY(head.getY() + 1);
        }
    }

    private void moveTail(Point head, Point tail) {
        if (Math.abs(head.getX() - tail.getX()) == 2 && Math.abs(head.getY() - tail.getY()) == 1) {
            tail.setX(tail.getX() + (tail.getX() - head.getX()) / -2);
            tail.setY(tail.getY() + (tail.getY() - head.getY()) * -1);
        } else if (Math.abs(head.getY() - tail.getY()) == 2 && Math.abs(head.getX() - tail.getX()) == 1) {
            tail.setX(tail.getX() + (tail.getX() - head.getX()) * -1);
            tail.setY(tail.getY() + (tail.getY() - head.getY()) / -2);
        } else if (Math.abs(head.getX() - tail.getX()) == 2 || Math.abs(head.getY() - tail.getY()) == 2) {
            tail.setX(tail.getX() + (tail.getX() - head.getX()) / -2);
            tail.setY(tail.getY() + (tail.getY() - head.getY()) / -2);
        }
    }
}
