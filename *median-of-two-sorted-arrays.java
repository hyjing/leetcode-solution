class Solution {
    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
        int m = nums1.length;
        int n = nums2.length;
        
        if (m > n) {
            int[] temp = nums1;
            nums1 = nums2;
            nums2 = temp;
            
            int tempNum = m;
            m = n;
            n = tempNum;
        }
        
        int iMin = 0;
        int iMax = m;
        int halfLen = (m + n + 1) / 2;
        
        while (iMin <= iMax) {
            int i = (iMin + iMax) / 2;
            int j = halfLen - i;
            
            if (i < m && nums1[i] < nums2[j - 1]) {
                iMin = i + 1;
            } else if (i > 0 && nums2[j] < nums1[i - 1]) {
                iMax = i - 1;
            } else {
                int left_max = 0;
                if (i == 0) {
                    left_max = nums2[j - 1];
                } else if (j == 0) {
                    left_max = nums1[i - 1];
                } else {
                    left_max = Math.max(nums1[i - 1], nums2[j - 1]);
                }
                
                if ((m + n) % 2 == 1) {
                    return left_max;
                }
                
                int right_min = 0;
                if (i == m) {
                    right_min = nums2[j];
                } else if (j == n) {
                    right_min = nums1[i];
                } else {
                    right_min = Math.min(nums1[i], nums2[j]);
                }
                
                return (left_max + right_min) / 2.0;
            }
        }
        return 0;
    }
}
