package aoc2022.solutions;

import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

import static org.junit.jupiter.api.Assertions.assertEquals;

@TestMethodOrder(MethodOrderer.Random.class)
class Day03Test {

    @Test
    void part1() {
        String expected = "Total Priority: 157";
        String actual = new Day03().part1("src/test/resources/Day03/testData");

        assertEquals(expected, actual);
    }

    @Test
    void part2() {
        String expected = "Total Priority: 70";
        String actual = new Day03().part2("src/test/resources/Day03/testData");

        assertEquals(expected, actual);
    }
}
