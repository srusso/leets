package net.sr89;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CoinChangeTest {
    CoinChange solution = new CoinChange();

    @ParameterizedTest
    @MethodSource("testCases")
    void coinChange(int expected, int[] coins, int amount) {
        assertEquals(expected, solution.coinChange(coins, amount));
    }

    private static Stream<Arguments> testCases() {
        return Stream.of(
                Arguments.of(3, new int[]{1, 2, 5}, 11),
                Arguments.of(2, new int[]{1}, 2),
                Arguments.of(1, new int[]{1, 2}, 2),
                Arguments.of(3, new int[]{1, 3, 5}, 7),
                Arguments.of(35, new int[]{357,239,73,52}, 9832)
        );
    }
}
