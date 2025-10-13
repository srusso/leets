package net.sr89;

public class BuySellStockCooldown {
    public int maxProfit(int[] prices) {
        int needToRest = 0, canSellOrRest = Integer.MIN_VALUE, canBuyOrRest = 0;

        for (int price : prices) {
            // needing to rest means you just sold!
            int justSold = needToRest;

            // you can get to this state only by just having sold...
            needToRest = canSellOrRest + price; // ... so sell

            // you can get to "canSellOrRest" either by...
            canSellOrRest = Math.max(
                    canSellOrRest, // ... keep holding (you bought earlier)
                    canBuyOrRest - price // ... or by selling
            );

            // you can get to "canBuyOrRest" state by either...
            canBuyOrRest = Math.max(
                    canBuyOrRest, // ... resting
                    justSold // ... or having just sold
            );
        }

        return Math.max(needToRest, canBuyOrRest);
    }
}
