package aoc2022.util;

import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

import java.util.Iterator;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;

@TestMethodOrder(MethodOrderer.Random.class)
class TestDailyInputReader {

    @Test
    void getInputLineByLine() {
        String input = "This is a line with\n" +
                "Many\n" +
                "Mew Lines\n" +
                "Added t\n" +
                "o it";

        Iterator<String> expected = Stream.of("This is a line with", "Many", "Mew Lines", "Added t", "o it").iterator();
        Iterator<String> actual = DailyInputReader.getInputLineByLine(input).iterator();
        while (expected.hasNext() && actual.hasNext())
            assertEquals(expected.next(), actual.next());
        assert !expected.hasNext() && !actual.hasNext();
    }

    @Test
    void getInputCharByChar() {
        String input = "This test!";

        Iterator<Character> expected = Stream.of('T', 'h', 'i', 's', ' ', 't', 'e', 's', 't', '!').iterator();
        Iterator<Character> actual = DailyInputReader.getInputCharByChar(input).iterator();
        while (expected.hasNext() && actual.hasNext())
            assertEquals(expected.next(), actual.next());
        assert !expected.hasNext() && !actual.hasNext();
    }

    @Test
    void getInputSplitOnX() {
        String input = "This test!";

        Iterator<String> expected = Stream.of("This", "test!").iterator();
        Iterator<String> actual = DailyInputReader.getInputSplitOnX(input, " ").iterator();
        while (expected.hasNext() && actual.hasNext())
            assertEquals(expected.next(), actual.next());
        assert !expected.hasNext() && !actual.hasNext();
    }
}