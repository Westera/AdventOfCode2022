package aoc2022.solutions;

import aoc2022.util.DailyInputReader;
import aoc2022.util.Day;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Day07 extends Day {

    public Day07() {

    }

    public Day07(String input) {
        super(input);
    }

    public static void main(String[] args) {
        new Day07("src/main/resources/Day07/input");
    }

    @Override
    public String part1(String path) {
        Directory root = buildDirectoryStructure(path);
        int total = root.flatten().stream().mapToInt(Directory::computeSize).filter(size -> size <= 100000).sum();
        return String.format("Total Sizes: %d", total);

    }

    @Override
    public String part2(String path) {
        Directory root = buildDirectoryStructure(path);
        int mustBeDeleted = 30000000 - 70000000 + root.computeSize();
        int result = root.flatten().stream().mapToInt(Directory::computeSize).sorted().
                filter(value -> value >= mustBeDeleted).findFirst().orElse(-1);
        return String.format("Directory Size: %d", result);
    }

    private Directory buildDirectoryStructure(String path) {
        try {
            List<String> rows = DailyInputReader.getInputFileToLines(path).skip(1).toList();
            Directory root = new Directory(null), workingDirectory = root;
            for (String row : rows) {
                if (row.startsWith("$ cd ..")) {
                    workingDirectory = workingDirectory.parent();
                } else if (row.startsWith("$ cd")) {
                    Directory directory = new Directory(workingDirectory);
                    workingDirectory.children().add(directory);
                    workingDirectory = directory;
                } else if (row.matches("\\d+.*")) {
                    int fileSize = Integer.parseInt(row.split(" ")[0]);
                    workingDirectory.fileSizes().add(fileSize);
                }
            }
            return root;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private record Directory(Directory parent, List<Directory> children, List<Integer> fileSizes) {

        public Directory(Directory parent) {
            this(parent, new ArrayList<>(), new ArrayList<>());
        }

        public int computeSize() {
            return fileSizes.stream().reduce(0, Integer::sum) + children.stream().
                    mapToInt(Directory::computeSize).sum();
        }

        public List<Directory> flatten() {
            ArrayList<Directory> flattened = new ArrayList<>(children.stream().map(Directory::flatten).
                    flatMap(List::stream).toList());
            flattened.add(this);
            return flattened;
        }
    }
}
