package com.yzd.java.kuangstudy.juc.single;

/**
 * Created by yzd on 2021/2/25
 */
// enum 本身也是一个Class类
public enum EnumSingle {
    INSTANCE;

    public EnumSingle getInstance() {
        return INSTANCE;
    }
}

class test {
    public static void main(String[] args) {
        EnumSingle single1 = EnumSingle.INSTANCE;
        EnumSingle single2 = EnumSingle.INSTANCE;

        System.out.println(single1.equals(single2));
    }
}
