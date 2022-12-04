package aoc2022.solutions;

import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

import static org.junit.jupiter.api.Assertions.assertEquals;

@TestMethodOrder(MethodOrderer.Random.class)
class Day04Test {

    @Test
    void part1() {
        String expected = "Number of covered: 2";
        String actual = new Day04().part1("src/test/resources/Day04/testData");

        assertEquals(expected, actual);
    }

    @Test
    void part2() {
        String expected = "Number of covered: 4";
        String actual = new Day04().part2("src/test/resources/Day04/testData");

        assertEquals(expected, actual);
    }
}
