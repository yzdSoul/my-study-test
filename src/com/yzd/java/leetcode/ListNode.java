package com.yzd.java.leetcode;

/**
 * Created by yzd on 2020/8/20
 */
public class ListNode {
    int val;
    ListNode next;

    public ListNode(int x) {
        val = x;
    }

    @Override
    public String toString() {
        return  val + ", " + next;
    }
}
