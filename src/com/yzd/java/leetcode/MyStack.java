package com.yzd.java.leetcode;

/**
 * Created by yzd on 2020/8/24
 */
//栈
public interface MyStack<Item> extends Iterable<Item>{

    MyStack<Item> push (Item item);

    Item pop () throws Exception;

    boolean isEmpty();

    int size();
}
