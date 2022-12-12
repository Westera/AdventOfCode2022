package aoc2022.solutions;

import aoc2022.util.DailyInputReader;
import aoc2022.util.Day;
import aoc2022.util.Point;

import java.io.IOException;
import java.util.*;

public class Day12 extends Day {

    private Map<Point, Integer> distances;

    public Day12() {

    }

    public Day12(String input) {
        super(input);
    }

    public static void main(String[] args) {
        new Day12("src/main/resources/Day12/input");
    }

    @Override
    public String part1(String path) {
        try {
            Point start = null, end = null;
            List<String> rows = DailyInputReader.getInputFileToLines(path).toList();
            int[][] heightMap = new int[rows.size()][];
            Arrays.setAll(heightMap, row -> new int[rows.get(0).trim().length()]);
            for (int y = 0; y < rows.size(); y++) {
                String row = rows.get(y);
                for (int x = 0; x < row.length(); x++) {
                    char character = row.charAt(x);
                    if (character == 'S') {
                        start = new Point(x, y);
                        character = 'a';
                    } else if (character == 'E') {
                        end = new Point(x, y);
                        character = 'z';
                    }
                    heightMap[y][x] = character;
                }
            }
            distances = new HashMap<>();
            distances.put(end, 0);
            int shortest = findShortestPath(end, start, heightMap);
            return String.format("Number of steps: %d", shortest);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public String part2(String path) {
        try {
            Point end = null;
            List<String> rows = DailyInputReader.getInputFileToLines(path).toList();
            int[][] heightMap = new int[rows.size()][];
            Arrays.setAll(heightMap, row -> new int[rows.get(0).trim().length()]);
            for (int y = 0; y < rows.size(); y++) {
                String row = rows.get(y);
                for (int x = 0; x < row.length(); x++) {
                    char character = row.charAt(x);
                    if (character == 'S') {
                        character = 'a';
                    } else if (character == 'E') {
                        end = new Point(x, y);
                        character = 'z';
                    }
                    heightMap[y][x] = character;
                }
            }
            distances = new HashMap<>();
            distances.put(end, 0);
            int shortest = findShortestPathWithContent(end, 'a', heightMap);
            return String.format("Number of steps: %d", shortest);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private int findShortestPath(Point start, Point goal, int[][] heightMap) {
        Set<Point> settled = new HashSet<>();
        Set<Point> unsettled = new HashSet<>();

        unsettled.add(start);
        while (unsettled.size() != 0) {
            Point current = calcNextPointWithShortestDistance(heightMap, settled, unsettled);
            if (current.equals(goal)) {
                return distances.get(goal);
            }
        }
        return -1;
    }

    private int findShortestPathWithContent(Point start, char goal, int[][] heightMap) {
        Set<Point> settled = new HashSet<>();
        Set<Point> unsettled = new HashSet<>();

        unsettled.add(start);
        while (unsettled.size() != 0) {
            Point current = calcNextPointWithShortestDistance(heightMap, settled, unsettled);
            if (heightMap[current.getY()][current.getX()] == 'a') {
                return distances.get(current);
            }
        }
        return -1;
    }

    private Point calcNextPointWithShortestDistance(int[][] heightMap, Set<Point> settled, Set<Point> unsettled) {
        Point current = getLowestDistancePoint(unsettled);
        unsettled.remove(current);
        int x = current.getX(), y = current.getY();
        Point next = new Point(x - 1, y);
        if (x != 0 && !settled.contains(next) && heightMap[y][x] - heightMap[y][x - 1] <= 1) {
            calcMinDistance(next, current);
            unsettled.add(next);
        }
        next = new Point(x + 1, y);
        if (x != heightMap[y].length - 1 && !settled.contains(new Point(x + 1, y)) && heightMap[y][x] - heightMap[y][x + 1] <= 1) {
            calcMinDistance(next, current);
            unsettled.add(next);
        }
        next = new Point(x, y - 1);
        if (y != 0 && !settled.contains(new Point(x, y - 1)) && heightMap[y][x] - heightMap[y - 1][x] <= 1) {
            calcMinDistance(next, current);
            unsettled.add(next);
        }
        next = new Point(x, y + 1);
        if (y != heightMap.length - 1 && !settled.contains(new Point(x, y + 1)) && heightMap[y][x] - heightMap[y + 1][x] <= 1) {
            calcMinDistance(next, current);
            unsettled.add(next);
        }
        settled.add(current);
        return current;
    }

    private Point getLowestDistancePoint(Set<Point> unsettled) {
        Point lowestDistancePoint = null;
        int lowestDistance = Integer.MAX_VALUE;
        for (Point point : unsettled) {
            int distance = distances.get(point);
            if (distance < lowestDistance) {
                lowestDistance = distance;
                lowestDistancePoint = point;
            }
        }
        return lowestDistancePoint;
    }

    private void calcMinDistance(Point current, Point source) {
        int sourceDistance = distances.get(source);
        if (distances.get(current) == null || sourceDistance + 1 < distances.get(current)) {
            distances.put(current, sourceDistance + 1);
        }
    }
}
