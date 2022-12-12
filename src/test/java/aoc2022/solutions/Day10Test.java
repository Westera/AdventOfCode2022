package aoc2022.solutions;

import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

import static org.junit.jupiter.api.Assertions.assertEquals;

@TestMethodOrder(MethodOrderer.Random.class)
class Day10Test {

    @Test
    void part1() {
        String expected = "Total signal strength: 13140";
        String actual = new Day10().part1("src/test/resources/Day10/testData");

        assertEquals(expected, actual);
    }

    @Test
    void part2() {
        String expected = """
                CRT Outprint:
                ##..##..##..##..##..##..##..##..##..##..
                ###...###...###...###...###...###...###.
                ####....####....####....####....####....
                #####.....#####.....#####.....#####.....
                ######......######......######......####
                #######.......#######.......#######.....
                """;
        String actual = new Day10().part2("src/test/resources/Day10/testData");

        assertEquals(expected, actual);
    }
}
