package aoc2022.solutions;

import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

import static org.junit.jupiter.api.Assertions.assertEquals;

@TestMethodOrder(MethodOrderer.Random.class)
class Day01Test {

    @Test
    void part1() {
        String expected = "Max total weight is: 24000";
        String actual = new Day01().part1("src/test/resources/Day01/testData");

        assertEquals(expected, actual);
    }

    @Test
    void part2() {
        String expected = "Max total weight is: 45000";
        String actual = new Day01().part2("src/test/resources/Day01/testData");

        assertEquals(expected, actual);
    }
}