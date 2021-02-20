package com.yzd.java.kuangstudy.Thread;

/**
 * Created by yzd on 2021/2/3
 */
public class TestThread01 extends Thread {
    @Override
    public void run() {
        for (int i = 0; i < 20; i++) {
            System.out.println("我在看代码");
        }
    }

    public static void main(String[] args) {
        new TestThread01().start();
        for (int i = 0; i < 2000; i++) {
            System.out.println("我在写代码！！");
        }
    }
}
