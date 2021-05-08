package com.yzd.java.kuangstudy.juc;

import java.util.concurrent.CountDownLatch;

/**
 * Created by yzd on 2021/2/22
 */
public class CountDownLatchTest {
    public static void main(String[] args) {
        CountDownLatch countDownLatch = new CountDownLatch(6);
        for (int i = 0; i < 6; i++) {
            new Thread(()->{
                System.out.println(Thread.currentThread().getName() + " go out");
                countDownLatch.countDown();
            },String.valueOf(i)).start();
        }
        try {
            countDownLatch.await();//等待计数器归零，然后再向下执行
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("Close Door");
    }
}
