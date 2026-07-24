class Solution {
    public int uniqueXorTriplets(int[] nums) {
        int n = nums.length;
        HashSet<Integer> set1 = new HashSet<>();
        for (int i=0; i<n; i++) {
            for (int j=i; j<n; j++) {
                set1.add(nums[i] ^ nums[j]);
            }
        }

        HashSet<Integer> set2 = new HashSet<>();
        for (int x : set1) {
            for (int num : nums) {
                set2.add(x ^ num);
            }
        }
        // SC(O(T)) where T is nearest 2powx of max(nums);
        // TC(O(2*maxEl * n)) + O(n2)
        return set2.size();
    }
}