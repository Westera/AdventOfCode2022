package aoc2022.solutions;

import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

import static org.junit.jupiter.api.Assertions.assertEquals;

@TestMethodOrder(MethodOrderer.Random.class)
class Day09Test {

    @Test
    void part1() {
        String expected = "Visited: 13";
        String actual = new Day09().part1("src/test/resources/Day09/testData");

        assertEquals(expected, actual);
    }

    @Test
    void part2_1() {
        String expected = "Visited: 1";
        String actual = new Day09().part2("src/test/resources/Day09/testData");

        assertEquals(expected, actual);
    }

    @Test
    void part2_2() {
        String expected = "Visited: 36";
        String actual = new Day09().part2("src/test/resources/Day09/testData2");

        assertEquals(expected, actual);
    }
}
