package org.example;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class PalindromeNumberMathTests {

    private Solution solution = new Solution();

    @Test
    void test121() {
        assertTrue(solution.isPalindrome(121));
    }

    @Test
    void test1221() {
        assertTrue(solution.isPalindrome(1221));
    }

    @Test
    void testNegative121() {
        assertFalse(solution.isPalindrome(-1221));
    }

    @Test
    void test10() {
        assertFalse(solution.isPalindrome(10));
    }

    @Test
    void test0() {
        assertTrue(solution.isPalindrome(0));
    }

    @Test
    void test1() {
        assertTrue(solution.isPalindrome(1));
    }

    @Test
    void test12345() {
        assertFalse(solution.isPalindrome(12345));
    }

    @Test
    void test1234321() {
        assertTrue(solution.isPalindrome(1234321));
    }

    @Test
    void testMaxInt() {
        assertFalse(solution.isPalindrome(2147483647));
    }

    @Test
    void testEndsWithZero() {
        assertFalse(solution.isPalindrome(1000021));
    }

    @Test
    void test1001() {
        assertTrue(solution.isPalindrome(1001));
    }
}