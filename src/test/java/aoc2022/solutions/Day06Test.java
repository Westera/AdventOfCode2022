package aoc2022.solutions;

import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

import static org.junit.jupiter.api.Assertions.assertEquals;

@TestMethodOrder(MethodOrderer.Random.class)
class Day06Test {

    @Test
    void part1_1() {
        String expected = "Processed Characters: 7";
        String actual = new Day06().part1("src/test/resources/Day06/testData1");

        assertEquals(expected, actual);
    }

    @Test
    void part1_2() {
        String expected = "Processed Characters: 5";
        String actual = new Day06().part1("src/test/resources/Day06/testData2");

        assertEquals(expected, actual);
    }

    @Test
    void part1_3() {
        String expected = "Processed Characters: 6";
        String actual = new Day06().part1("src/test/resources/Day06/testData3");

        assertEquals(expected, actual);
    }

    @Test
    void part1_4() {
        String expected = "Processed Characters: 10";
        String actual = new Day06().part1("src/test/resources/Day06/testData4");

        assertEquals(expected, actual);
    }

    @Test
    void part1_5() {
        String expected = "Processed Characters: 11";
        String actual = new Day06().part1("src/test/resources/Day06/testData5");

        assertEquals(expected, actual);
    }

    @Test
    void part2_1() {
        String expected = "Processed Characters: 19";
        String actual = new Day06().part2("src/test/resources/Day06/testData1");

        assertEquals(expected, actual);
    }

    @Test
    void part2_2() {
        String expected = "Processed Characters: 23";
        String actual = new Day06().part2("src/test/resources/Day06/testData2");

        assertEquals(expected, actual);
    }

    @Test
    void part2_3() {
        String expected = "Processed Characters: 23";
        String actual = new Day06().part2("src/test/resources/Day06/testData3");

        assertEquals(expected, actual);
    }

    @Test
    void part2_4() {
        String expected = "Processed Characters: 29";
        String actual = new Day06().part2("src/test/resources/Day06/testData4");

        assertEquals(expected, actual);
    }

    @Test
    void part2_5() {
        String expected = "Processed Characters: 26";
        String actual = new Day06().part2("src/test/resources/Day06/testData5");

        assertEquals(expected, actual);
    }
}
