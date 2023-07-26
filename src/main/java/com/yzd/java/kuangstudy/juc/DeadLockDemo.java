package com.yzd.java.kuangstudy.juc;

import java.util.concurrent.TimeUnit;

/**
 * Created by yzd on 2021/2/26
 */
public class DeadLockDemo {
    public static void main(String[] args) {

        String lockA = "lockA";
        String lockB = "lockB";

        new Thread(new MyThreadTest(lockA,lockB),"T1").start();
        new Thread(new MyThreadTest(lockB,lockA),"T2").start();
    }

}

class MyThreadTest implements Runnable {
    private String lockA;
    private String lockB;

    public MyThreadTest(String lockA, String lockB) {
        this.lockA = lockA;
        this.lockB = lockB;
    }

    @Override
    public void run() {
        synchronized (lockA) {
            System.out.println(Thread.currentThread().getName() + "lockA：" + lockA + "=>get" + lockB);
            try {
                TimeUnit.SECONDS.sleep(2);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            synchronized (lockB) {
                System.out.println(Thread.currentThread().getName() + "lockB：" + lockB + "=>get" + lockA);

            }
        }

    }
}
