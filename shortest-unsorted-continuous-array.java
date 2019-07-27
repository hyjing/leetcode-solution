// 581. Shortest Unsorted Continuous Subarray
// iterate from left and then from right to find last seen unsorted element index, then the result if minus of two indexes
class Solution {
    public int findUnsortedSubarray(int[] nums) {
        if (nums == null || nums.length == 0) return 0;
        int n = nums.length;
        int end = -2;
        int begin = -1;
        int max = nums[0];
        int min = nums[n - 1];
        
        for (int i = 1; i < n; i++) {
            max = Math.max(max, nums[i]);
            if (nums[i] < max) end = i;
            min = Math.min(min, nums[n - i - 1]);
            if (nums[n - i - 1] > min) begin = n - i - 1;
        }
        
        return end - begin + 1;
    }
}
