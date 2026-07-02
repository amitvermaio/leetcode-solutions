class Solution {
    Integer[][] dp;

    private int max(int a, int b, int c) {
        return Math.max(a, Math.max(b, c));
    }

    private int solve(int[] prices, int day, int n, int buy) {
        if (day >= n) {
            return 0;
        }

        if (dp[day][buy] != null) {
            return dp[day][buy];
        }

        int profit = 0;
        if (buy == 1) { // 1 means abhi buy kar liya 
            // yha maine day wale din stock buy kar liya aur ab bfind karne ja rha hu ki kis din sell karu taki profit Max ho jaaye.
            // buy me 0 isliye paas kiya ki abhi maine buy kiya aur abhi mujhe sell wali value chahiye.
            int take = solve(prices, day+1, n, 0) - prices[day]; // profit = sp - cp
            // bas yhi kar rha hu sell price return hoga aur current day ki price '-' karne se profit pata chalega

            int notTake = solve(prices, day+1, n, 1);
            // abhi buy nhi kiya to aage buy kar sakta ku isliye notTake me 1 paas kiya

            profit = max(profit, take, notTake);
        } else { // ye wo case hai jime sell karna hai
            // aaj ka jo profit hai vo + call se aage hone wale profit
            int sell = prices[day] + solve(prices, day+2, n, 1);
            // isme sell kiya to ek din ka cooldown period hai isliye day+2 se shuru kiya aur uss din se buy kar sakta hai stock

            int notSell = solve(prices, day+1, n, 0);

            profit = max(profit, sell, notSell);
        }

        return dp[day][buy] = profit;
    }

    public int maxProfit(int[] prices) {
        int n = prices.length;
        int buy = 1; // 1-> true, 0, false making it like this coz we need to do memoization of this as well

        dp = new Integer[n+1][2]; // day(max n days) & buy(0/1)
        return solve(prices, 0, n, buy);
    }
}