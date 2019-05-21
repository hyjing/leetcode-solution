// k sum general recursive solution: visit all possible solutions by checking each element in the array, minus the target 
// by current element and pass it to k - 1 sum problem, eventually it will be a two sum.
// 1: 2-sum  15: 3-sum  18: 4-sum 
class Solution {
    public List<List<Integer>> fourSum(int[] nums, int target) {
        return kSum(nums, target, 4);
    }
    
    public List<List<Integer>> kSum(int[] nums, int target, int k) {
        Arrays.sort(nums);
        if (nums == null || nums.length < k || k <= 0) {
            return Collections.emptyList();
        }
        
        return k == 1 ? oneSum(nums, target) : kSumRecur(nums, target, k, 0);
    }
    
    public List<List<Integer>> kSumRecur(int[] nums, int target, int k, int index) {
        List<List<Integer>> res = new LinkedList();
        
        if (k == 2) {
            twoSum(res, nums, target, index, nums.length - 1);
            return res;
        }
        
        for (int i = index; i < nums.length - k + 1; i++) {
            if (i > index && nums[i] == nums[i - 1]) continue;
            
            List<List<Integer>> temp = kSumRecur(nums, target - nums[i], k - 1, i + 1);
            
            if (temp != null) {
                for (List<Integer> list : temp) {
                    list.add(0, nums[i]);
                }
                res.addAll(temp);
            }
            
            while (i < nums.length - 1 && nums[i] == nums[i + 1]) i++;
        }
        
        return res;
    }
    
    public void twoSum(List<List<Integer>> res, int[] nums, int target, int start, int end) {
        while (start < end) {
            int sum = nums[start] + nums[end];
            
            if (sum == target) {
                res.add(new LinkedList<Integer>(Arrays.asList(nums[start], nums[end])));
                
                while (start < end && (nums[start] == nums[start + 1])) start++;
                while (start < end && (nums[end] == nums[end - 1])) end--;
                start++;
                end--;
            } else if (sum < target) {
                start++;
            } else {
                end--;
            }
        } 
    }
    
    public List<List<Integer>> oneSum(int[] nums, int target) {
        List<List<Integer>> res = new ArrayList<>();
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] == target && (i == 0 || nums[i] != nums[i - 1])) {
                res.add(Arrays.asList(nums[i]));
            }
        }
        return res;
    }
}
