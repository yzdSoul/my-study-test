package com.yzd.java.kuangstudy.Thread;

/**
 * Created by yzd on 2021/2/18
 */

//不建议使用stop或者destroy等过时方法
public class TestStop implements Runnable {
    private boolean flag = true;

    @Override
    public void run() {
        int i = 0;
        while (flag) {
            System.out.println("run....Thread"+i++);
        }
    }
// 设置一个公开的方法停止线程 转换标志位
    public void stop() {
        this.flag = false;

    }

    public static void main(String[] args) {
        TestStop testStop = new TestStop();
        new Thread(testStop).start();
        for (int i = 0; i < 1000; i++) {
            System.out.println("main"+i);
            if (i == 900) {
                //切换标志位
                testStop.stop();
                System.out.println("线程停止了");
            }
        }
    }
}
