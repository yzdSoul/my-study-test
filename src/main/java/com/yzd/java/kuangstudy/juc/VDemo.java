package com.yzd.java.kuangstudy.juc;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by yzd on 2021/2/24
 */
public class VDemo {
    private static AtomicInteger  num = new AtomicInteger();

    public static void add() {
//        num++;
        num.getAndIncrement();
    }

    public static void main(String[] args) {

        for (int i = 1; i <= 20; i++) {
            new Thread(()->{
                for (int j = 0; j < 1000; j++) {
                    add();
                }
            }).start();
        }

        while (Thread.activeCount() > 2) {
            Thread.yield();
        }
        System.out.println(Thread.currentThread().getName()+" "+num);
    }
}
