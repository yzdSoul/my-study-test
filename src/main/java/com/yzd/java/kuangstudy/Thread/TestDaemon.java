package com.yzd.java.kuangstudy.Thread;

/**
 * Created by yzd on 2021/2/18
 */

//测试守护线程
public class TestDaemon {

    public static void main(String[] args) {
        God god = new God();
        Man man = new Man();
        Thread thread = new Thread(god);
        thread.setDaemon(true);
        thread.start();

        new Thread(man).start();
    }
}


class God implements Runnable {

    @Override
    public void run() {
        while (true) {
            System.out.println("上帝守护");
        }
    }
}

class Man implements Runnable {

    @Override
    public void run() {
        for (int i = 0; i < 36500; i++) {
            System.out.println("开心活着每一天");
        }
        System.out.println("古德拜");
    }
}