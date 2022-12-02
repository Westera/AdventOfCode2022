package aoc2022.solutions;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class Day02Test {

    @Test
    void part1() {
        String expected = "Total Score: 48";
        String actual = new Day02().part1("src/test/resources/Day02/testData");

        assertEquals(expected, actual);
    }

    @Test
    void part2() {
        String expected = "Total Score: 53";
        String actual = new Day02().part2("src/test/resources/Day02/testData");

        assertEquals(expected, actual);
    }
}
