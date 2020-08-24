package com.yzd.java.leetcode;

import java.util.Iterator;

/**
 * Created by yzd on 2020/8/24
 */
public class ArrayStack<Item> implements MyStack<Item>{

    private Item[] items = (Item[]) new Object[1];

    private int N = 0;


    @Override
    public MyStack<Item> push(Item item) {
        //check();
        items[N++] = item;
        return this;
    }

    @Override
    public Item pop() throws Exception {
        return null;
    }

    @Override
    public boolean isEmpty() {
        return false;
    }

    @Override
    public int size() {
        return 0;
    }

    @Override
    public Iterator iterator() {
        return null;
    }
}
