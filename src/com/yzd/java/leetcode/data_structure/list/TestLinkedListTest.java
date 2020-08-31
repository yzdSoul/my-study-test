package com.yzd.java.leetcode.data_structure.list;

import org.junit.Test;


/**
 * Created by yzd on 2020/8/27
 */
public class TestLinkedListTest {

    private TestLinkedList linkedList = new TestLinkedList();

    public ListNode creatList(){
        ListNode head = new ListNode(0);
        head.next = new ListNode(1);
        head.next.next = new ListNode(2);
        head.next.next.next = new ListNode(3);
        head.next.next.next.next = new ListNode(4);
        head.next.next.next.next.next = new ListNode(5);
        head.next.next.next.next.next.next = new ListNode(6);
        head.next.next.next.next.next.next.next = new ListNode(7);
        return head;
    }

    @Test
    public void reverseList() {
        ListNode head = creatList();

        System.out.println(head);
        ListNode reverseList = linkedList.reverseList(head);
        System.out.println(reverseList);
    }

    @Test
    public void reverseListN(){

        ListNode head = creatList();
        System.out.println(head);
        ListNode reverseListN = linkedList.reverseListN(head, 3);
        System.out.println(reverseListN);
    }
}