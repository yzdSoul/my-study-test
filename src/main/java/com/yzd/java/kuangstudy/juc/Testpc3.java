package com.yzd.java.kuangstudy.juc;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by yzd on 2021/2/20
 */

public class Testpc3 {
    public static void main(String[] args) {
        int loop = 10;
        Data3 data = new Data3();
        new Thread(()->{
            for (int i = 0; i < loop; i++) {
                data.printA(loop);
            }
        },"α").start();
        new Thread(()->{
            for (int i = 0; i < loop; i++) {
                data.printB(loop);
            }
        },"β").start();
        new Thread(()->{
            for (int i = 0; i < loop; i++) {
                data.printC(loop);
            }
        },"γ").start();
    }
}
class Data3 {
    private Lock lock = new ReentrantLock();

    private Condition condition = lock.newCondition();
    private int number = 1;
    public void printA(int loop) {
        lock.lock();
        try {
            while (number%loop != 1) {
                condition.await();
            }
            System.out.println(Thread.currentThread().getName()+"->A");
            number = 2;
            condition.signalAll();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }
    public void printB(int loop) {
        lock.lock();
        try {
            while (number%loop != 2) {
                condition.await();
            }
            System.out.println(Thread.currentThread().getName()+"->B");
            number = 3;
            condition.signalAll();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }
    public void printC(int loop) {
        lock.lock();
        try {
            while (number%loop != 3) {
                condition.await();
            }
            System.out.println(Thread.currentThread().getName() + "->C");
            number = 1;
            condition.signalAll();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }
}
