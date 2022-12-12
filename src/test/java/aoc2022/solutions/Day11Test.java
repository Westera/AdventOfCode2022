package aoc2022.solutions;

import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

import static org.junit.jupiter.api.Assertions.assertEquals;

@TestMethodOrder(MethodOrderer.Random.class)
class Day11Test {

    @Test
    void part1() {
        String expected = "Monkeys to chase: 10605";
        String actual = new Day11().part1("src/test/resources/Day11/testData");

        assertEquals(expected, actual);
    }

    @Test
    void part2() {
        String expected = "Monkeys to chase: 2713310158";
        String actual = new Day11().part2("src/test/resources/Day11/testData");

        assertEquals(expected, actual);
    }
}
