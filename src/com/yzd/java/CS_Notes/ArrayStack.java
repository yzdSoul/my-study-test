package com.yzd.java.CS_Notes;

import com.yzd.java.CS_Notes.MyStack;

import java.util.Iterator;

/**
 * Created by yzd on 2020/8/24
 */
public class ArrayStack<Item> implements MyStack<Item> {

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
        if (isEmpty()){
            throw new Exception("stack is empty");
        }

        Item item = items[--N];

        //check();

        items[N] = null;

        return item;
    }

    @Override
    public boolean isEmpty() {
        return N == 0;
    }

    @Override
    public int size() {
        return 0;
    }

    @Override
    public Iterator iterator() {
        return new Iterator<Item>() {

            private int i = N;
            @Override
            public boolean hasNext() {
                return i > 0;
            }

            @Override
            public Item next() {
                return items[--i];
            }
        };
    }

    private void check(){
        if (N >= items.length){
            resize(2 * items.length);
        } else if (N > 0 && N < items.length / 4 ){
            resize(items.length / 2);
        }
    }

    private void resize(int size) {
        Item[] temp = (Item[]) new Object[size];

        for (int i = 0; i < N; i++) {
            temp[i] = items[i];
        }

        items = temp;
    }
}
