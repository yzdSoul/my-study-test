package com.yzd.java.kuangstudy.juc.single;

/**
 * Created by yzd on 2021/2/25
 */
//静态内部类
public class Holder {
    private Holder() {

    }

    public static Holder getInstance() {
        return InnerClass.HOLDER;
    }
    public static class InnerClass {
        private static final Holder HOLDER = new Holder();
    }
}
