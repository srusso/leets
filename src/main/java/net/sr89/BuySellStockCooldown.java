package net.sr89;

public class BuySellStockCooldown {
    public int maxProfit(int[] prices) {
        int sold = 0, hold = Integer.MIN_VALUE, rest = 0;
        for (int price : prices) {
            int prvSold = sold;
            sold = hold + price;
            hold = Math.max(hold, rest - price);
            rest = Math.max(rest, prvSold);
        }
        return Math.max(sold, rest);
    }
}
