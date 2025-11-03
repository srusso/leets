package net.sr89;

import java.util.HashMap;
import java.util.Map;

/**
 * <a href="https://leetcode.com/problems/coin-change/">Leetcode link</a>
 */
public class CoinChange {
    public int coinChange(int[] coins, int amount) {
        if (amount == 0) {
            return 0;
        }

        Map<Integer, Integer> dp = new HashMap<>();

        for (int coin : coins) {
            dp.put(coin, 1);
        }

        dp.put(0, 0);

        if (amount == 2) {
            return dp.containsKey(2) ? 1 : dp.containsKey(1) ? 2 : -1;
        }

        for (int i = 1; i <= amount; i++) {
            final int upper = i / 2 + 1;
            for (int j = 1; j < upper; j++) {
                int jSolution = dp.getOrDefault(j, -1);
                if (jSolution == -1) {
                    continue;
                }
                int remaining = i - j;
                int remainingSolution = dp.getOrDefault(remaining, -1);
                if (remainingSolution == -1) {
                    continue;
                }
                dp.compute(i, (_, value) ->
                        Math.min(value == null ? Integer.MAX_VALUE : value, jSolution + remainingSolution));
            }
        }

        return dp.getOrDefault(amount, -1);
    }
}
