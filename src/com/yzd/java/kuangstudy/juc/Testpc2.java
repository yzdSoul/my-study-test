package com.yzd.java.kuangstudy.juc;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by yzd on 2021/2/20
 */
public class Testpc2 {
    public static void main(String[] args) {
        Data2 data = new Data2();

        new Thread(()->{
            for (int i = 0; i < 20; i++) {
                data.increment();
            }
        },"a").start();

        new Thread(()->{
            for (int i = 0; i < 20; i++) {
                data.decrement();
            }
        },"b").start();

        new Thread(()->{
            for (int i = 0; i < 20; i++) {
                data.increment();
            }
        },"c").start();

        new Thread(()->{
            for (int i = 0; i < 20; i++) {
                data.decrement();
            }
        },"d").start();
    }
}

class Data2 {
    private int number;

    Lock lock = new ReentrantLock();

    Condition condition = lock.newCondition();

    public void increment() {
        lock.lock();
        try {
            while (number != 0) {
                condition.await();
            }
            number++;
            System.out.println(Thread.currentThread().getName() + "->" + number);
            condition.signalAll();
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            lock.unlock();
        }

    }

    public void decrement() {
        lock.lock();
        try {
            while (number == 0) {
                condition.await();
            }
            number--;
            System.out.println(Thread.currentThread().getName() + "->" + number);
            condition.signalAll();
        } catch (Exception e) {
            e.printStackTrace();

        }finally {
            lock.unlock();
        }

    }
}