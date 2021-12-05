package com.yzd.java.kuangstudy.Thread;

/**
 * Created by yzd on 2021/2/18
 */
public class TestRace implements Runnable {

    public static String winner;

    @Override
    public void run() {
        for (int i = 0; i <= 100; i++) {

            if (Thread.currentThread().getName() == "兔子" && i % 10 == 0) {
                try {
                    Thread.sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }


            boolean flag = gameOver(i);
            if (flag) {
                break;
            }
            System.out.println(Thread.currentThread().getName()+"--> 跑了" +i+"步");
        }
    }

    private boolean gameOver(int steps) {
        if (winner != null) {
            return true;
        }
        if (steps >= 100) {
            winner = Thread.currentThread().getName();
            System.out.println("winner is " + winner);
            return true;
        }
        return false;
    }


    public static void main(String[] args) {
        TestRace race = new TestRace();
        new Thread(race, "兔子").start();
        new Thread(race, "乌龟").start();
    }
}
