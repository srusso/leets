package net.sr89;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;

class BuySellStockCooldownTest {
    final BuySellStockCooldown buySell = new BuySellStockCooldown();

    @ParameterizedTest
    @MethodSource("testCases")
    void runTests(int expected, int[] prices) {
        assertEquals(expected, buySell.maxProfit(prices));
    }

    private static Stream<Arguments> testCases() {
        return Stream.of(
                Arguments.of(3, new int[]{1, 2, 3, 0, 2})
        );
    }
}