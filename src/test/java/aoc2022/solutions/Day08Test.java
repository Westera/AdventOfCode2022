package aoc2022.solutions;

import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

import static org.junit.jupiter.api.Assertions.assertEquals;

@TestMethodOrder(MethodOrderer.Random.class)
class Day08Test {

    @Test
    void part1() {
        String expected = "Amount of Visible Trees: 21";
        String actual = new Day08().part1("src/test/resources/Day08/testData");

        assertEquals(expected, actual);
    }

    @Test
    void part2() {
        String expected = "Best Scenic Score: 8";
        String actual = new Day08().part2("src/test/resources/Day08/testData");

        assertEquals(expected, actual);
    }
}
