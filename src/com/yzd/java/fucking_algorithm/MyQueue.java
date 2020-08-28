package com.yzd.java.fucking_algorithm;

import java.util.Stack;

/**
 * Created by yzd on 2020/8/28
 */
class MyQueue {

    private Stack<Integer> s1,s2;
    /** 添加元素到队尾 */

    public MyQueue(){
        s1 = new Stack<>();
        s2 = new Stack<>();
    }
    public void push(int x){
        s1.push(x);
    }

    /** 删除队头的元素并返回 */
    public int pop(){
        peek();
        return s2.pop();
    }

    /** 返回队头元素 */
    public int peek(){
        if (s2.isEmpty()){
            //把 s1元素 压入 s2
            while (!s1.isEmpty()){
                s2.push(s1.pop());
            }
        }
        return s2.peek();
    }

    /** 判断队列是否为空 */
    public boolean empty(){
        return s1.isEmpty() && s2.isEmpty();
    }
}