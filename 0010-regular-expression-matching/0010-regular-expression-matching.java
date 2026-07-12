class Solution {
    Boolean[][] dp = new Boolean[21][21];
    boolean solve(String s, String p, int i, int j) {
        if (dp[i][j] != null) {
            return dp[i][j];
        }

        if (j == p.length()) {
            return dp[i][j] = i == s.length();
        }

        boolean firstCharMatch = false;
        if (i<s.length() && 
            (s.charAt(i) == p.charAt(j) || p.charAt(j) == '.')
        ) {
            firstCharMatch = true;
        }

        boolean ans;
        if (j+1<p.length() && p.charAt(j+1)=='*') {
            boolean takeAsterisk = firstCharMatch && solve(s, p, i+1, j);
            boolean skipAsterisk = solve(s, p, i, j+2);

            ans = takeAsterisk || skipAsterisk;
        } else {
            ans = firstCharMatch && solve(s, p, i+1, j+1);
        }

        return dp[i][j] = ans;
    } 

    public boolean isMatch(String s, String p) {
        return solve(s, p, 0, 0);
    }
}