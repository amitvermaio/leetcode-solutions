class Solution {
    Integer[][] dp;

    private int solve(String text1, String text2, int i, int j) {
        if (i>=text1.length() || j>=text2.length()) {
            return 0;
        }

        if (dp[i][j] != null) {
            return dp[i][j];
        }

        if (text1.charAt(i) == text2.charAt(j)) {
            return dp[i][j] = 1 + solve(text1, text2, i+1, j+1); 
        } 

        return dp[i][j] = Math.max(
            solve(text1, text2, i+1, j),
            solve(text1, text2, i, j+1)
        ); 
    }

    public int longestCommonSubsequence(String text1, String text2) {
        dp = new Integer[text1.length()+1][text2.length()+1];
        return solve(text1, text2, 0, 0);
    }
}