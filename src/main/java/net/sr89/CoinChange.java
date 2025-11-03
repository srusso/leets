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

        for (int i = 1 ; i <= amount ; i++) {
            for (int j = i - 1 ; j > 1 ; j--) {
                int jSolution = dp.getOrDefault(j, 0);
                int remaining = i - j;
                for (int coin : coins) {
                    if (remaining % coin == 0) {
                        int iSolution = Math.min(
                                dp.getOrDefault(i, Integer.MAX_VALUE),
                                jSolution + (remaining / coin)
                        );
                        dp.put(i, iSolution);
                    }
                }
            }
        }

        return dp.getOrDefault(amount, -1);
    }
}
