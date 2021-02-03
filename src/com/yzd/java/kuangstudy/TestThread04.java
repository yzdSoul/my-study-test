package com.yzd.java.kuangstudy;

/**
 * Created by yzd on 2021/2/3
 */
public class TestThread04 implements Runnable{

    private int ticketNums = 10;


    @Override
    public void run() {
        while (true) {
            if (ticketNums <= 0) {
                break;
            }
            try {
                Thread.sleep(200);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName()+"拿到了第"+ticketNums--+"票");
        }
    }

    public static void main(String[] args) {
        TestThread04 thread04 = new TestThread04();

        new Thread(thread04,"小明").start();
        new Thread(thread04,"老师").start();
        new Thread(thread04,"黄牛党").start();

    }
}
