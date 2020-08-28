package com.yzd.java.CS_Notes;

import java.util.Iterator;

/**
 * Created by yzd on 2020/8/26
 *
 * 需要使用链表的头插法来实现，因为头插法中最后压入栈的元素在链表的开头，它的 next 指针指向前一个压入栈的元素，
 * 在弹出元素时就可以通过 next 指针遍历到前一个压入栈的元素从而让这个元素成为新的栈顶元素。
 */
public class ListStack<Item>implements MyStack<Item> {

    private Node top = null;
    private int N = 0;
    private class Node {
        Item item;
        Node next;
    }

    @Override
    public MyStack<Item> push(Item item) {
        Node newTop = new Node();

        newTop.item = item;
        newTop.next = top;

        top = newTop;

        N++;
        return this;
    }

    @Override
    public Item pop() throws Exception {

        if (isEmpty()){
            throw new Exception("stack is empty");
        }

        Item item = top.item;

        top = top.next;
        N--;
        return item;
    }

    @Override
    public boolean isEmpty() {
        return N == 0;
    }

    @Override
    public int size() {
        return N;
    }

    @Override
    public Iterator iterator() {
        return new Iterator() {
            private Node cur = top;

            @Override
            public boolean hasNext() {
                return cur != null;
            }

            @Override
            public Item next() {
                Item item = cur.item;
                cur = cur.next;
                return item;
            }
        };
    }
}
