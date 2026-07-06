class Solution {
    public int removeCoveredIntervals(int[][] intervals) {
        Arrays.sort(intervals, (a, b) -> {
            if (a[0] == b[0]) {
                return Integer.compare(b[1], a[1]);
            } else {
                return Integer.compare(a[0], b[0]);
            }
        });

        int[] last = {Integer.MAX_VALUE, Integer.MAX_VALUE};
        int count = 0;
        for (int[] inter : intervals) {
            if (last[0] <= inter[0] && inter[1] <= last[1]) {
                count++;
            } else {
                last = inter;
            }
        }
        return intervals.length - count;
    }
}