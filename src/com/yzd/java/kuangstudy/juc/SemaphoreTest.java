package com.yzd.java.kuangstudy.juc;

import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

/**
 * Created by yzd on 2021/2/22
 */
public class SemaphoreTest {
    public static void main(String[] args) {
        //线程数量 停车位 限流
        // 多个共享资源互斥使用
        Semaphore semaphore = new Semaphore(4);

        for (int i = 0; i <= 6; i++) {
            new Thread(()->{
               // 得到 acquire()  如果已经满了 等待被释放为止
                try {
                    semaphore.acquire();
                    System.out.println(Thread.currentThread().getName()+"抢到车位");
                    TimeUnit.SECONDS.sleep(2);
                    System.out.println(Thread.currentThread().getName()+"离开车位");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }finally{
                    semaphore.release();  // 释放 release() 会将当前的信号量释放 然后唤醒等待的线程
                }

            }).start();
        }
    }
}
