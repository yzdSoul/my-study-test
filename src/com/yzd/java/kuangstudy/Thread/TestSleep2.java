package com.yzd.java.kuangstudy.Thread;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by yzd on 2021/2/18
 */
public class TestSleep2 {
    public static void main(String[] args) {
        Date startTime = new Date(System.currentTimeMillis());//获取系统当前时间

        while (true) {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(new SimpleDateFormat("HH:mm:ss").format(startTime));
            startTime = new Date(System.currentTimeMillis());//更新当前时间
        }
    }
    public static void tenDown() {
        int num = 10;
        while (true) {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(num--);
            if (num < 0) {
                break;
            }
        }
    }
}
