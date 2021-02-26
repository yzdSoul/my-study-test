package com.yzd.java.kuangstudy.juc;

import java.util.concurrent.TimeUnit;

/**
 * Created by yzd on 2021/2/24
 */
public class JMMDemo {
    // 不加volatile 程序就会死循环
    //保证可见性 不保证原子性 可以避免指令重排
    private static volatile int num = 0;
    public static void main(String[] args) throws InterruptedException {
        new Thread(()->{
            while (num == 0) {

            }
        }).start();
        TimeUnit.SECONDS.sleep(1);
        num = 1;
        System.out.println(num);
    }
}
