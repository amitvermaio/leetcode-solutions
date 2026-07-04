class Solution {
    int n;
    Integer[][] dp;
    private int solve(String s1, String s2, int i, int j) {
        if (i >= n || j >= n) {
            return 0;
        }

        if (dp[i][j] != null) {
            return dp[i][j];
        }

        if (s1.charAt(i) == s2.charAt(j)) {
            return dp[i][j] = 1 + solve(s1, s2, i+1, j+1);
        }

        return dp[i][j] = Math.max(
            solve(s1, s2, i+1, j),
            solve(s1, s2, i, j+1)
        );
    }
    public int longestPalindromeSubseq(String s) {
        n = s.length();
        dp = new Integer[n+1][n+1];
        StringBuilder sb = new StringBuilder(s); 
        return solve(s, sb.reverse().toString(), 0, 0);
    }
}