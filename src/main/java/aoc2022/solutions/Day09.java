package aoc2022.solutions;

import aoc2022.util.DailyInputReader;
import aoc2022.util.Day;

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
                    visited.add(new Point(tail.x, tail.y));
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

                    visited.add(new Point(tails.get(tails.size() - 1).x, tails.get(tails.size() - 1).y));
                }
            });
            return String.format("Visited: %d", visited.size());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private void moveHead(String direction, Point head) {
        switch (direction) {
            case "L" -> head.x = head.x - 1;
            case "R" -> head.x = head.x + 1;
            case "D" -> head.y = head.y - 1;
            case "U" -> head.y = head.y + 1;
        }
    }

    private void moveTail(Point head, Point tail) {
        if (Math.abs(head.x - tail.x) == 2 && Math.abs(head.y - tail.y) == 1) {
            tail.x = tail.x + (tail.x - head.x) / -2;
            tail.y = tail.y + (tail.y - head.y) * -1;
        } else if (Math.abs(head.y - tail.y) == 2 && Math.abs(head.x - tail.x) == 1) {
            tail.x = tail.x + (tail.x - head.x) * -1;
            tail.y = tail.y + (tail.y - head.y) / -2;
        } else if (Math.abs(head.x - tail.x) == 2 || Math.abs(head.y - tail.y) == 2) {
            tail.x = tail.x + (tail.x - head.x) / -2;
            tail.y = tail.y + (tail.y - head.y) / -2;
        }
    }

    private static class Point {

        private int x;
        private int y;

        public Point(int x, int y) {
            this.x = x;
            this.y = y;
        }

        @Override
        public int hashCode() {
            return Objects.hash(x, y);
        }

        @Override
        public boolean equals(Object obj) {
            if (obj instanceof Point) {
                return x == ((Point) obj).x && y == ((Point) obj).y;
            }
            return false;
        }
    }
}
