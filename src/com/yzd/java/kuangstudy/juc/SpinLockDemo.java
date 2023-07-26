package com.yzd.java.kuangstudy.juc;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicReference;

/**
 * Created by yzd on 2021/2/26
 */
//自旋锁
public class SpinLockDemo {

    AtomicReference<Thread> atomicReference = new AtomicReference<>();

    //加锁
    public void myLock() {
        Thread thread = Thread.currentThread();

        System.out.println(Thread.currentThread().getName() + "==>mylock");


        while (!atomicReference.compareAndSet(null, thread)) {

        }
    }

    //解锁
    public void myUnLock() {
        Thread thread = Thread.currentThread();

        System.out.println(Thread.currentThread().getName() + "==>myUnlock");

        atomicReference.compareAndSet(thread, null);
    }


    public static void main(String[] args) throws InterruptedException {

        SpinLockDemo lock = new SpinLockDemo();

        new Thread(() -> {
            lock.myLock();
            try {

                TimeUnit.SECONDS.sleep(5);
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                lock.myUnLock();
            }

        }, "T1").start();

        //确保T1 先执行
        TimeUnit.SECONDS.sleep(1);

        new Thread(() -> {
            lock.myLock();
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                lock.myUnLock();
            }

        }, "T2").start();
    }
}
