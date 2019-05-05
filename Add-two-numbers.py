# Definition for singly-linked list.
# class ListNode(object):
#     def __init__(self, x):
#         self.val = x
#         self.next = None

class Solution(object):
    def addTwoNumbers(self, l1, l2):
        """
        :type l1: ListNode
        :type l2: ListNode
        :rtype: ListNode
        """
        addAhead = 0
        outputList = ListNode(0)
        nextPtr = outputList
        # if l1 == None and l2 == None:
        #     return None
        while l1 != None and l2 != None:
            node = ListNode(0)
            numToAdd = l1.val + l2.val
            if addAhead == 1:
                numToAdd += 1
                addAhead = 0
            if numToAdd >= 10:
                addAhead = 1
                numToAdd -= 10
            node.val = numToAdd
            nextPtr.next = node
            nextPtr = nextPtr.next
            l1 = l1.next
            l2 = l2.next
        
        while l1 != None:
            node = ListNode(0)
            val = l1.val
            if addAhead == 1:
                val += 1
                addAhead = 0
            if val >= 10:
                addAhead = 1
                val -= 10
            node.val = val
            nextPtr.next = node
            nextPtr = nextPtr.next
            l1 = l1.next
        
        while l2 != None:
            node = ListNode(0)
            val = l2.val
            if addAhead == 1:
                val += 1
                addAhead = 0
            if val >= 10:
                addAhead = 1
                val -= 10
            node.val = val
            nextPtr.next = node
            nextPtr = nextPtr.next
            l2 = l2.next
        
        if addAhead == 1:
            nextPtr.next = ListNode(1)
        
        return outputList.next
