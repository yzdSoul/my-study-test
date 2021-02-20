package com.yzd.java.kuangstudy.Thread;

/**
 * Created by yzd on 2021/2/3
 */
public class TestThread03 implements Runnable {
    @Override
    public void run() {
        for (int i = 0; i < 200; i++) {
            System.out.println("我在看代码---");
        }
    }

    public static void main(String[] args) {
        TestThread03 testThread03 = new TestThread03();

        Thread thread = new Thread(testThread03);
        thread.start();
        for (int i = 0; i < 2000; i++) {
            System.out.println("我在写代码！！！");
        }
    }
}
