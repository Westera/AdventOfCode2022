package aoc2022.solutions;

import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

import static org.junit.jupiter.api.Assertions.assertEquals;

@TestMethodOrder(MethodOrderer.Random.class)
class Day07Test {

    @Test
    void part1() {
        String expected = "Total Sizes: 95437";
        String actual = new Day07().part1("src/test/resources/Day07/testData");

        assertEquals(expected, actual);
    }

    @Test
    void part2() {
        String expected = "Directory Size: 24933642";
        String actual = new Day07().part2("src/test/resources/Day07/testData");

        assertEquals(expected, actual);
    }
}
