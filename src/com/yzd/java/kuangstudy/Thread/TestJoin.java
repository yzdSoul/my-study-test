package com.yzd.java.kuangstudy.Thread;

/**
 * Created by yzd on 2021/2/18
 */
public class TestJoin implements Runnable {

    @Override
    public void run() {
        for (int i = 0; i < 1000; i++) {
            System.out.println("ViP线程来了" + i);
        }
    }

    public static void main(String[] args) throws InterruptedException {
        TestJoin testJoin = new TestJoin();
        Thread thread = new Thread(testJoin);
        thread.start();


        for (int i = 0; i < 500; i++) {
            if (i == 200) {
                thread.join();
            }
            System.out.println("main" + i);
        }
    }
}
