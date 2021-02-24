package com.yzd.java.kuangstudy.juc;

import java.util.Collection;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.TimeUnit;

/**
 * Created by yzd on 2021/2/22
 */
public class BlockQueueTest {
    public static void main(String[] args) throws InterruptedException {
        test2();
    }

    public static void test() {
        ArrayBlockingQueue blockingQueue = new ArrayBlockingQueue<>(3);
        System.out.println(blockingQueue.add("a"));
        System.out.println(blockingQueue.add("b"));
        System.out.println(blockingQueue.add("c"));
//        java.lang.IllegalStateException: Queue full
//        System.out.println(blockingQueue.add("d"));

        System.out.println("===================");
        System.out.println(blockingQueue.remove());
        System.out.println(blockingQueue.remove());
        System.out.println(blockingQueue.remove());
        // java.util.NoSuchElementException
//        System.out.println(blockingQueue.remove());

    }

    public static void test2() throws InterruptedException {
        ArrayBlockingQueue blockingQueue = new ArrayBlockingQueue<>(3);
        System.out.println(blockingQueue.offer("a"));
        System.out.println(blockingQueue.offer("b"));
        System.out.println(blockingQueue.offer("c"));
//        返回 false
//        System.out.println(blockingQueue.offer("d"));
        //返回队首元素
//        System.out.println(blockingQueue.peek());

        System.out.println("===================");
        System.out.println(blockingQueue.poll());
        System.out.println(blockingQueue.poll());
        System.out.println(blockingQueue.poll());

        //返回 null
//        System.out.println(blockingQueue.poll());
        //返回当前元素  找不到会报 java.util.NoSuchElementException
//        System.out.println(blockingQueue.element());
        // 设置超时多长时间 放弃
        blockingQueue.offer("d", 2, TimeUnit.SECONDS);
    }

}
