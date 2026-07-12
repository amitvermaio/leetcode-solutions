class Solution {
    public int[] arrayRankTransform(int[] arr) {
        int[] temp = arr.clone();
        Arrays.sort(temp);
        HashMap<Integer, Integer> mp = new HashMap<>();
        int count = 1;
        for (int num : temp) {
            if (!mp.containsKey(num)) {
                mp.put(num, count++);
            }
        }

        count = 0;
        int[] ranks = new int[arr.length];
        for (int num : arr) {
            ranks[count++] = mp.get(num);
        }

        return ranks;
    }
}