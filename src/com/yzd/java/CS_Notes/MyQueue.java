package com.yzd.java.CS_Notes;

/**
 * Created by yzd on 2020/8/28
 */
public interface MyQueue<Item> extends Iterable<Item> {

    int size();

    boolean isEmpty();

    MyQueue<Item> add(Item item);

    Item remove() throws Exception;
}
