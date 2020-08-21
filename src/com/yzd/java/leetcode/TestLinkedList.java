package com.yzd.java.leetcode;

/**
 * Created by yzd on 2020/8/20
 */
public class TestLinkedList {
/*
160. Intersection of Two Linked Lists (Easy)
* 要求时间复杂度为 O(N)，空间复杂度为 O(1)。如果不存在交点则返回 null。
设 A 的长度为 a + c，B 的长度为 b + c，其中 c 为尾部公共部分长度，可知 a + c + b = b + c + a。
当访问 A 链表的指针访问到链表尾部时，令它从链表 B 的头部开始访问链表 B；同样地，当访问 B 链表的指针访问到链表尾部时，令它从链表 A 的头部开始访问链表 A。这样就能控制访问 A 和 B 两个链表的指针能同时访问到交点。
如果不存在交点，那么 a + b = b + a，以下实现代码中 l1 和 l2 会同时为 null，从而退出循环。
* */
    public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        ListNode l1 = headA, l2 = headB;
        while (l1 != l2){
            l1 = (l1 == null) ? headB : l1.next;
            l2 = (l2 == null) ? headA : l2.next;
        }
        return l1;
    }

/*
206. Reverse Linked List (Easy)
* */
    //递归
    public ListNode reverseList(ListNode head){
        if (head == null || head.next == null){
            return head;
        }
        ListNode next = head.next;
        ListNode newhead =reverseList(next);
        next.next = head;
        head.next = null;
        return newhead;
    }

    //插头发
    public ListNode reverseList_Demo2(ListNode head){
        ListNode newHead = new ListNode(-1);
        while (head != null){
            ListNode next = head.next;
            head.next = newHead.next;
            newHead.next = head;
            head = next;
        }
        return newHead.next;
    }
}

