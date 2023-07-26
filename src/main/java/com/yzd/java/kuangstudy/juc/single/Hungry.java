package com.yzd.java.kuangstudy.juc.single;

/**
 * Created by yzd on 2021/2/25
 */
//饿汉式 单例
public class Hungry {
    private Hungry() {

    }
    private static final Hungry HUNGRY = new Hungry();

    public static Hungry getInstance() {
        return HUNGRY;
    }
}
