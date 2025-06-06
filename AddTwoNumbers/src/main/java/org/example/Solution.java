package org.example;

// https://leetcode.com/problems/add-two-numbers/?envType=problem-list-v2&envId=math

class ListNode {
    int val;
    ListNode next;

    ListNode() {
    }

    ListNode(int val) {
        this.val = val;
    }

    ListNode(int val, ListNode next) {
        this.val = val;
        this.next = next;
    }
}

class Solution {
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        ListNode first = new ListNode(0);
        ListNode value = first;
        int nextLine = 0;

        while (l1 != null || l2 != null || nextLine != 0) {
            int x = (l1 != null) ? l1.val : 0;
            int y = (l2 != null) ? l2.val : 0;

            int sum = x + y + nextLine;
            nextLine = sum / 10;

            value.next = new ListNode(sum % 10);
            value = value.next;

            if (l1 != null) l1 = l1.next;
            if (l2 != null) l2 = l2.next;
        }

        return first.next;
    }
}
