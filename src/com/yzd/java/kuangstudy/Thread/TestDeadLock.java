package com.yzd.java.kuangstudy.Thread;

/**
 * Created by yzd on 2021/2/19
 */
public class TestDeadLock {
    public static void main(String[] args) {
        Makeup g1 = new Makeup(0, "灰姑凉");
        Makeup g2 = new Makeup(1, "公主");
        g1.start();
        g2.start();
    }
}

class Lipstick {

}

class Mirror {

}

class Makeup extends Thread {

    static Lipstick lipstick = new Lipstick();
    static Mirror mirror = new Mirror();

    int choice;

    String grilName;

    Makeup(int choice, String grilName) {
        this.choice = choice;
        this.grilName = grilName;
    }

    @Override
    public void run() {
        try {
            makeup();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private void makeup() throws InterruptedException {
        if (choice == 0) {
            synchronized (lipstick) {
                System.out.println(this.grilName + "获得口红的锁");
                Thread.sleep(1000);
            }

            synchronized (mirror) {
                System.out.println(this.grilName + "获得镜子的锁");
            }
        } else {
            synchronized (mirror) {
                System.out.println(this.grilName + "获得镜子的锁");
                Thread.sleep(1000);
            }

            synchronized (lipstick) {
                System.out.println(this.grilName + "获得口红的锁");
            }
        }
    }
}