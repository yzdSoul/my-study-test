package com.yzd.java.kuangstudy.juc;

import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;

/**
 * Created by yzd on 2021/2/24
 */
public class FunctionTest {//函数型接口 断定型接口
    public static void main(String[] args) {
        //输入什么返回什么
        Function<String, String> function =  str -> str;
        System.out.println(function.apply("123"));
        //判断输入字符串是否为空
        Predicate <String> predicate = String::isEmpty;
        System.out.println(predicate.test("111"));
        //消费型接口 有参数 无返回值
        Consumer <String> consumer = str -> {
            System.out.println("消费完了");
        };
        consumer.accept("123");
        //供给型接口 没有参数 只有返回值
        Supplier supplier = () -> 1024;
        System.out.println(supplier.get());
    }
}
