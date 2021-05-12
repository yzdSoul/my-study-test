package com.yzd.java.kuangstudy.juc;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by yzd on 2021/2/22
 */

//java.util.ConcurrentModificationException  并发修改异常
public class UnsafeMapTest {
    public static void main(String[] args) {
        //工作中基本不用 HashMap
//      默认  new HashMap<>(16,0.75);

        //解决方法
//       1, Collections.synchronizedMap(new HashMap<>());
//       2, Map<String, String> map = new ConcurrentHashMap<>();
        Map<String, String> map = new ConcurrentHashMap<>();
        for (int i = 0; i < 30; i++) {
            new Thread(() -> {
                map.put(Thread.currentThread().getName(), UUID.randomUUID().toString().substring(0, 5));
                System.out.println(map);
            }, String.valueOf(i)).start();
        }
    }
}
