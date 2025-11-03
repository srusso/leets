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

        for (int curentAmount = 1; curentAmount <= amount; curentAmount++) {
            for (int coin : coins) {
                int currentAmountMinusCoin = curentAmount - coin;
                int currentAmountMinusCoinSolution = dp.getOrDefault(currentAmountMinusCoin, -1);
                if (currentAmountMinusCoinSolution == -1) {
                    continue;
                }
                dp.compute(curentAmount, (_, value) ->
                        Math.min(value == null ? Integer.MAX_VALUE : value, currentAmountMinusCoinSolution + 1));
            }
        }

        return dp.getOrDefault(amount, -1);
    }
}
