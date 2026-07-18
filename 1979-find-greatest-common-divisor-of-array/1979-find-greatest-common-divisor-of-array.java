class Solution {
    private int gcd(int a, int b) {
        if (b == 0) return a;
        return gcd(b, a%b);
    }

    public int findGCD(int[] nums) {
        int min = nums[0];
        int max = nums[0];
        int n = nums.length;

        for (int i=1; i<n; i++) {
            min = Math.min(nums[i], min);
            max = Math.max(nums[i], max);
        }

        return gcd(max, min);
    }
}