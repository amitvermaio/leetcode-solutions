class Solution {
    int m, n;
    Integer[][] dp;
    private int solve(String s1, String s2, int i, int j) {
        if (i>=m && j>=n) {
            return 0;
        }

        if (dp[i][j] != null) {
            return dp[i][j];
        }

        if (i >= m) {
            return dp[i][j] = (int) s2.charAt(j) + solve(s1, s2, i, j+1);
        } 

        if (j >= n) {
            return dp[i][j] = (int) s1.charAt(i) + solve(s1, s2, i+1, j);
        }

        if (s1.charAt(i) == s2.charAt(j)) {
            return dp[i][j] = solve(s1, s2, i+1, j+1);
        }

        return dp[i][j] = Math.min(
            (int) s1.charAt(i) + solve(s1, s2, i+1, j),
            (int) s2.charAt(j) + solve(s1, s2, i, j+1)
        );
    }

    public int minimumDeleteSum(String s1, String s2) {
        m = s1.length();
        n = s2.length();
        dp = new Integer[m+1][n+1];
        return solve(s1,  s2, 0, 0);
    }
}