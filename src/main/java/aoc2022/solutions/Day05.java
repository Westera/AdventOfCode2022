package aoc2022.solutions;

import aoc2022.util.DailyInputReader;
import aoc2022.util.Day;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Stack;
import java.util.stream.Collectors;
import java.util.stream.Stream;

interface Crane {
    void move(Stream<List<Integer>>instructions, Stack<String>[] columns);
}
public class Day05 extends Day {

    public Day05() {

    }

    public Day05(String input) {
        super(input);
    }

    public static void main(String[] args) {
        new Day05("src/main/resources/Day05/input");
    }

    @Override
    public String part1(String path) {
        Crane crateMover9000 = this::moveOneByOne;
        return String.format("Top Crates: %s", moveBoxes(crateMover9000, path));
    }

    @Override
    public String part2(String path) {
        Crane crateMover9001 = this::moveAll;
        return String.format("Top Crates: %s", moveBoxes(crateMover9001, path));
    }

    private String moveBoxes(Crane crane, String path) {
        try {
            List<String> splitInputData = DailyInputReader.getInputSplitOnX(DailyInputReader.
                    getInputAsString(path),"\n\n").toList();
            Stack<String>[] stacks = getStacksOfBoxes(splitInputData.get(0));
            crane.move(DailyInputReader.getInputLineByLine(splitInputData.get(1)).
                    map(row -> DailyInputReader.getInputSplitOnX(row, "move|from|to| ").
                            filter(s -> !s.isEmpty()).map(Integer::parseInt).toList()), stacks);
            return Arrays.stream(stacks).map(Stack::pop).map(s -> String.valueOf(s.charAt(1))).
                    collect(Collectors.joining());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private Stack<String>[] getStacksOfBoxes(String input) {
        List<String> rows = DailyInputReader.getInputLineByLine(input).filter(s -> !s.contains("1")).toList();
        int numberOfColumns = rows.get(rows.size()-1).replaceAll(" ", "").length()/3;
        Stack<String>[] stacks = new Stack[numberOfColumns];
        for(int i = rows.size()-1 ; i >= 0 ; i--) {
            String row = rows.get(i);
            for(int j = 0, column = 0 ; j < row.length() ; j = j + 4, column++) {
                String box = row.substring(j, j+3);
                if (!box.equals("   ")) {
                    Stack<String> stack = stacks[column];
                    if (stack == null) {
                        stack = new Stack<>();
                        stacks[column] = stack;
                    }
                    stack.push(box);
                }
            }
        }
        return stacks;
    }

    private void moveOneByOne(Stream<List<Integer>> instructions, Stack<String>[] columns) {
        instructions.forEachOrdered(instruction -> {
            Stack<String> from = columns[instruction.get(1)-1], to = columns[instruction.get(2)-1];

            for (int i = 0 ; i < instruction.get(0) ; i++) {
                to.push(from.pop());
            }
        });
    }

    private void moveAll(Stream<List<Integer>> instructions, Stack<String>[] columns) {
        instructions.forEachOrdered(instruction -> {
            Stack<String> from = columns[instruction.get(1) - 1], to = columns[instruction.get(2) - 1], temp = new Stack<>();
            for (int i = 0; i < instruction.get(0); i++) {
                temp.push(from.pop());
            }
            for (int i = 0; i < instruction.get(0); i++) {
                to.push(temp.pop());
            }
        });
    }
}
