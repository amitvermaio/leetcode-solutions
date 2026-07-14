class Solution {
    final static int MOD = 1_000_000_007;

    private boolean isPrime(char c) {
        return c == '2' || c == '3' || c == '5' || c == '7';
    }

    private int solve(String s, int minLength, int i, int partsLeft, Integer[][] dp) {
        // Base case: Agar parts 0 ho gaye
        if (partsLeft == 0) {
            // Agar string bhi puri khatam ho gayi toh valid tarika hai
            return i == s.length() ? 1 : 0;
        }
        
        // Agar parts bache hain but string khatam ho gayi
        if (i == s.length()) return 0;
        
        // Rule 1: Har partition prime se shuru hona chahiye
        if (!isPrime(s.charAt(i))) return 0;

        if (dp[i][partsLeft] != null) return dp[i][partsLeft];

        int ways = 0;
        
        // Loop chalao aage ke possible end points ke liye
        // Jahaan cut lagega, wo index 'j' hai
        for (int j = i + minLength - 1; j < s.length(); j++) {
            
            // Rule 2: Partition non-prime pe end hona chahiye
            if (!isPrime(s.charAt(j))) {
                
                // Rule 3: Agla 8partition prime se start hona chahiye 
                // (Ya fir yeh string ka aakhiri character ho)
                if (j == s.length() - 1 || isPrime(s.charAt(j + 1))) {
                    ways = (ways + solve(s, minLength, j + 1, partsLeft - 1, dp)) % MOD;
                }
            }
        }

        return dp[i][partsLeft] = ways;
    }

    public int beautifulPartitions(String s, int k, int minLength) {
        if (k * minLength > s.length()) return 0;
        if (!isPrime(s.charAt(0)) || isPrime(s.charAt(s.length() - 1))) return 0;

        Integer[][] dp = new Integer[s.length()][k + 1];
        return solve(s, minLength, 0, k, dp);
    }
}