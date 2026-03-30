package net.sr89.problems;

/**
 * <a href="https://leetcode.com/problems/coin-change/">Leetcode link</a>
 */
public class CoinChange {
    public int coinChange(int[] coins, int amount) {
        if (amount == 0) {
            return 0;
        }

        int [] dp = new int[10001];

        for (int coin : coins) {
            if (coin < dp.length) {
                dp[coin] = 1;
            }
        }

        for (int currentAmount = 1; currentAmount <= amount; currentAmount++) {
            for (int coin : coins) {
                int currentAmountMinusCoin = currentAmount - coin;
                if (currentAmountMinusCoin < 0) {
                    continue;
                }
                int currentAmountMinusCoinSolution = dp[currentAmountMinusCoin];
                if (currentAmountMinusCoinSolution == 0) {
                    continue;
                }
                dp[currentAmount] = Math.min(dp[currentAmount] == 0 ? Integer.MAX_VALUE : dp[currentAmount], currentAmountMinusCoinSolution + 1);
            }
        }

        return dp[amount] == 0 ? -1 : dp[amount];
    }
}
