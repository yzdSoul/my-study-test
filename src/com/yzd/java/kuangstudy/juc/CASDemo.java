package com.yzd.java.kuangstudy.juc;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by yzd on 2021/2/25
 */
public class CASDemo {

    public static void main(String[] args) {

        AtomicInteger atomicInteger = new AtomicInteger(2020);

        atomicInteger.compareAndSet(2020, 2021);

        System.out.println(atomicInteger.get());

    }
}
