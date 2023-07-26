package com.yzd.java.kuangstudy.Thread;

/**
 * Created by yzd on 2021/2/18
 */
//不安全的买票 线程不安全
public class UnsafeBuyTicket {
    public static void main(String[] args) {
        BuyTicket station = new BuyTicket();

        new Thread(station, "黄牛").start();
        new Thread(station, "我").start();
        new Thread(station, "你们").start();

    }
}

class BuyTicket implements Runnable {

    private int ticketNums = 10;

    boolean flag = true;
    @Override
    public void run() {
        //买票
        while (flag) {
            buy();
        }
    }

    private synchronized void buy() {
        //判断是否有票
        if (ticketNums <= 0) {
            flag = false;
            return;
        }
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(Thread.currentThread().getName()+"拿到"+ticketNums--);
    }
}
