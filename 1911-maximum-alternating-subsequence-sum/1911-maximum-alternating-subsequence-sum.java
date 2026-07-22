class Solution {
    long[][] dp;

    public long solve(int[] nums, int i, boolean even) {
        if (i == nums.length) return 0;

        int flagIdx = even ? 1 : 0;

        if (dp[i][flagIdx] != -1) {
            return dp[i][flagIdx];
        }

        long skip = solve(nums, i+1, even);
        int val = nums[i];
        if (!even) val = -val;
        long take = val + solve(nums, i+1, !even);

        return dp[i][flagIdx] = Math.max(skip, take);
    }

    public long maxAlternatingSum(int[] nums) { 
        dp = new long[nums.length][2];
        
        for (int i=0; i<nums.length; i++) {
            dp[i][0] = -1;
            dp[i][1] = -1;
        }

        return solve(nums, 0, true);
    }
}