class Solution {
    public int subarraySum(int[] nums, int k) {
        HashMap<Integer, Integer> mp = new HashMap<>();
        int res = 0;
        int n = nums.length;
        int[] prefix = new int[n];
        prefix[0] = nums[0];
        for (int i=1; i<n; i++) prefix[i] = nums[i] + prefix[i-1];
        
        for (int i=0; i<n; i++) {
            if (prefix[i] == k) res++;
            int num = prefix[i]-k;
            if (mp.containsKey(num)) {
                res += mp.get(num);
            }
            mp.put(prefix[i], mp.getOrDefault(prefix[i], 0) + 1);
        }
        return res;
    }
}