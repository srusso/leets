package net.sr89.problems;

public class BuySellStockCooldown {
    public int maxProfit(int[] prices) {

        // there are only three states (you have to rest, you can rest or sell, you can rest or buy)
        // these variables represent the maximum profit at any "state" at any point in time

        // you have just sold
        int mustRest = 0,
                // you have just held or bought
                holdingStock = Integer.MIN_VALUE,
                // you have just rested for the first time, or you just kept resting
                canBuyOrRest = 0;

        for (int price : prices) {
            // we only need the "mustRest" state from the previous iteration
            int previousMustRest = mustRest;

            // now let's go to the next state, for the next iteration

            // go to this state in the only possible way: by selling what you are holding
            mustRest = holdingStock + price;

            // go to "holdingStock" either by...
            holdingStock = Math.max(
                    holdingStock, // ... keep holding (you bought earlier)
                    canBuyOrRest - price // ... or by buying this share
            );

            // go to "canBuyOrRest" state by either...
            canBuyOrRest = Math.max(
                    canBuyOrRest, // ... keep resting
                    previousMustRest // ... or having just rested for the first time
            );
        }

        return Math.max(mustRest, canBuyOrRest);
    }
}
