# dict comprehension syntax
# d = {key: value for (key, value) in iterable}
class Solution(object):
    def twoSum(self, nums, target):
        """
        :type nums: List[int]
        :type target: int
        :rtype: List[int]
        """
        
        sum = []
        numDict = {}
        for i in range(len(nums)):
            num = nums[i]
            reduNum = target - num
            if numDict.get(reduNum) != None:
                return [numDict.get(reduNum), i]
            numDict[num] = i
