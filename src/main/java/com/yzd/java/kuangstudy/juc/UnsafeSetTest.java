package com.yzd.java.kuangstudy.juc;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;
import java.util.concurrent.CopyOnWriteArraySet;

/**
 * Created by yzd on 2021/2/22
 */

//java.util.ConcurrentModificationException 并发修改异常
public class UnsafeSetTest {
    public static void main(String[] args) {

        //解决方法
//       1, Set<String> set = Collections.synchronizedSet(new HashSet<>());
//       2, Set<String> set = new CopyOnWriteArraySet<>();

        //引出问题 HashSet 是怎么实现的
        // HashSet 就是HashMap 只不过只使用了key  值是一个固定的常量
        Set<String> set = new CopyOnWriteArraySet<>();
        for (int i = 0; i < 10; i++) {
            new Thread(()->{
                set.add(UUID.randomUUID().toString().substring(0, 5));
                System.out.println(set);
            },String.valueOf(i)).start();
        }
    }
}
