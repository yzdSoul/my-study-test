package com.yzd.java.leetcode.data_structure.list;

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


    //反转前N个节点
    public ListNode reverseListN(ListNode head,int n){
        ListNode successor = null;
        if (n == 1){
            //记录第 n + 1 个节点
            successor = head.next;
            return head;
        }

        ListNode next = head.next;
        ListNode newhead =reverseListN(next,n - 1);
        head.next.next = head;
        head.next = successor;
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

//    21. Merge Two Sorted Lists (Easy)  归并两个有序的链表

    public ListNode mergeTwoList(ListNode l1,ListNode l2){
        if (l1 == null) return l2;
        if (l2 == null) return l1;
        if (l1.val < l2.val) {
            l1.next = mergeTwoList(l1.next, l2);
            return l1;
        } else {
            l2.next = mergeTwoList(l2.next, l1);
            return l2;
        }
    }

//    83. Remove Duplicates from Sorted List (Easy) 从有序链表中删除重复节点
//    Given 1->1->2, return 1->2.
//    Given 1->1->2->3->3, return 1->2->3.

    public ListNode deleteDuplicates(ListNode head){
        if (head == null || head.next == null) return head;
        head.next = deleteDuplicates(head.next);
        return head.val == head.next.val ? head.next : head;
    }

//    19. Remove Nth Node From End of List (Medium) 删除链表的倒数第 n 个节点

//    Given linked list: 1->2->3->4->5, and n = 2.
//    After removing the second node from the end, the linked list becomes 1->2->3->5.

    public ListNode removeNthFromEnd(ListNode head, int n){
        ListNode fast = head;
        //剩下的节点数作为计数，删除第length - n 个节点
        while (n-- > 0){
            fast = fast.next;
        }
        if (fast == null) return head.next;
        ListNode slow = head;
        while (fast.next != null){
            fast = fast.next;
            slow = slow.next;
        }
        slow.next = slow.next.next;
        return head;
    }

//    24. Swap Nodes in Pairs (Medium)
//    Given 1->2->3->4, you should return the list as 2->1->4->3.
    public ListNode swapParis(ListNode head){
        ListNode node = new ListNode(-1);
        node.next = head;
        ListNode pre = node;
        while (pre.next != null && pre.next.next != null){
            ListNode l1 = pre.next, l2 = pre.next.next;
            l1.next = l2.next;
            l2.next = l1;
            pre.next = l2;
            pre = l1;
        }
        return node.next;
    }
}


