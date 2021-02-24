package com.yzd.java.kuangstudy.juc;

import java.util.*;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * Created by yzd on 2021/2/22
 */
// java.util.ConcurrentModificationException  并发修改异常
public class UnsafeListTest {
    public static void main(String[] args) {
        //并发下ArrayList 不安全
        //解决方法
//      1,  List<String> list = new Vector<>();
//      2,  List<String> list = Collections.synchronizedList(new ArrayList<>());()
//      3,  List<String> list = new CopyOnWriteArrayList<>();
        /*
        *   CopyOnWrite 写入时复制  COW 计算机程序设计领域的一种优化策略
        *   多个线程调用的时候 list 读取的时候，固定的，写入（覆盖）
        *   在写入的时候避免覆盖，造成数据问题
        *   读写分离
        *
        * 为什么用 CopyOnWriteArrayList？
        * Vector 的add方法使用了Synchronized 修饰效率低
        * CopyOnWriteArrayList 的add方法使用 ReentrantLock加锁 写入时复制  效率提高
        * */
        List<String> list = new CopyOnWriteArrayList<>();
        for (int i = 0; i < 10; i++) {
            new Thread(()->{
                list.add(UUID.randomUUID().toString().substring(0, 5));
                System.out.println(list);
            },String.valueOf(i)).start();

        }
    }
}
