class Solution {
    public int maxProfit(int[] prices) {
        int max = prices[0];
        int min = prices[0];
        int diff = 0;
        for (int i=1; i<prices.length; i++) {
            if (prices[i] < min) {
                min = prices[i];
                max = prices[i];
            } else {
                max = prices[i];
            }
            diff = Math.max(diff, max-min);
        }
        return diff;
    }
}