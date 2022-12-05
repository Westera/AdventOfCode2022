package aoc2022.solutions;

import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

import static org.junit.jupiter.api.Assertions.assertEquals;

@TestMethodOrder(MethodOrderer.Random.class)
class Day05Test {

    @Test
    void part1() {
        String expected = "Top Crates: CMZ";
        String actual = new Day05().part1("src/test/resources/Day05/testData");

        assertEquals(expected, actual);
    }

    @Test
    void part2() {
        String expected = "Top Crates: MCD";
        String actual = new Day05().part2("src/test/resources/Day05/testData");

        assertEquals(expected, actual);
    }
}