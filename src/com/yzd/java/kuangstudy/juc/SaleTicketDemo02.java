package com.yzd.java.kuangstudy.juc;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by yzd on 2021/2/20
 */


/*
 *   Synchronized 和 Lock 区别
 *   1，Synchronized 是java关键字 Lock是一个java类
 *   2，Synchronized 无法判断获取锁的状态  Lock可以判断是否获取到了锁
 *   3，Synchronized 会自动释放锁  lock 必须要手动释放锁 否则会出现死锁
 *   4，Synchronized 线程1(阻塞)，线程（2）等待 ;lock 锁不回等待
 *   5，Synchronized 可重入锁 不可中断的，非公平锁；lock 可重入锁 可以判断锁，非公平（默认，可设置）
 *   6，Synchronized  适合锁少量代码 ，Lock 适合锁大量同步代码
 * */
public class SaleTicketDemo02 {
    public static void main(String[] args) {
        Ticket2 ticket = new Ticket2();
        new Thread(() -> {
            for (int i = 0; i < 60; i++) ticket.sale();
        }, "A").start();
        new Thread(() -> {
            for (int i = 0; i < 60; i++) ticket.sale();
        }, "B").start();
        new Thread(() -> {
            for (int i = 0; i < 60; i++) ticket.sale();
        }, "C").start();
    }
}

class Ticket2 {
    private int number = 50;
    //第一步new 锁
    Lock lock = new ReentrantLock();
    public  void sale() {
        //第二部加锁
        lock.lock();
        try {
            if (number > 0) {
                System.out.println(Thread.currentThread().getName() + "卖出了第" + number-- + "票，剩余" + number);
            }
        } catch (Exception e) {
            e.printStackTrace();
            //第三步解锁
        } finally{
            lock.unlock();
        }

    }
}