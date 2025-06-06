package org.example;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;

class AddTwoNumbersTest {

    private ListNode buildList(int... digits) {
        ListNode first = new ListNode();
        ListNode value = first;
        for (int d : digits) {
            value.next = new ListNode(d);
            value = value.next;
        }
        return first.next;
    }

    private int[] toArray(ListNode node) {
        List<Integer> result = new ArrayList<>();
        while (node != null) {
            result.add(node.val);
            node = node.next;
        }
        return result.stream().mapToInt(i -> i).toArray();
    }

    @Test
    void testAddTwoNumbers() {
        Solution solution = new Solution();

        ListNode l1 = buildList(2, 4, 3);
        ListNode l2 = buildList(5, 6, 4);

        ListNode result = solution.addTwoNumbers(l1, l2);

        assertArrayEquals(new int[]{7, 0, 8}, toArray(result));
    }

    @Test
    void testZero() {
        Solution solution = new Solution();
        ListNode l1 = buildList(0);
        ListNode l2 = buildList(0);
        ListNode result = solution.addTwoNumbers(l1, l2);
        assertArrayEquals(new int[]{0}, toArray(result));
    }

    @Test
    void testDifferentLength() {
        Solution solution = new Solution();
        ListNode l1 = buildList(1, 8);
        ListNode l2 = buildList(0);
        ListNode result = solution.addTwoNumbers(l1, l2);
        assertArrayEquals(new int[]{1, 8}, toArray(result));
    }
}
