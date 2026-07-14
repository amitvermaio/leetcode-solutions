class Solution {
public:
    int MOD = 1e9 + 7;
    int subsequencePairCount(vector<int>& nums) {
        int n = nums.size();

        int maxEl = *max_element(begin(nums), end(nums));

        int t[n+1][maxEl+1][maxEl+1];

        for (int gcd1=0; gcd1<=maxEl; gcd1++) {
            for (int gcd2=0; gcd2<=maxEl; gcd2++) {
                bool bothNonEmpty = (gcd1!=0 && gcd2!=0);
                bool gcdsMatch = gcd1==gcd2;

                t[n][gcd1][gcd2] = (bothNonEmpty && gcdsMatch) ? 1 : 0;
            }
        }

        for (int i=n-1; i>=0; i--) {
            for(int gcd1=maxEl; gcd1>=0; gcd1--) {
                for (int gcd2=maxEl; gcd2>=0; gcd2--) {
                    int skip = t[i+1][gcd1][gcd2];
                    int take1 = t[i+1][__gcd(gcd1, nums[i])][gcd2];
                    int take2 = t[i+1][gcd1][__gcd(gcd2, nums[i])];

                    t[i][gcd1][gcd2] = (0LL + skip + take1 + take2) % MOD;
                }
            }
        }

        return t[0][0][0];
    }
};