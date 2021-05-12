package com.yzd.java.kuangstudy.Thread;

/**
 * Created by yzd on 2021/2/18
 */
public class TestLambda2 {


    public static void main(String[] args) {

        Ilove love;
        love = a-> System.out.println("i love u -->"+a);
        // lambda表达式 在只有一行代码的情况下可以简化花括号
        //  接口必须是函数式接口 只有一个方法的接口
        // 多个参数可以去掉类型 要去掉全去掉  需要加括号
        love.love(520);
    }
}

interface Ilove {
    void love(int a);
}


