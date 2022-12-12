package aoc2022.solutions;

import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

import static org.junit.jupiter.api.Assertions.assertEquals;

@TestMethodOrder(MethodOrderer.Random.class)
class Day12Test {

    @Test
    void part1() {
        String expected = "Number of steps: 31";
        String actual = new Day12().part1("src/test/resources/Day12/testData");

        assertEquals(expected, actual);
    }

    @Test
    void part2() {
        String expected = "Number of steps: 29";
        String actual = new Day12().part2("src/test/resources/Day12/testData");

        assertEquals(expected, actual);
    }
}
