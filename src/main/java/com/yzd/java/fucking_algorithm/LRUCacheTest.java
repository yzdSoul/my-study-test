package com.yzd.java.fucking_algorithm;


import org.junit.Test;

/**
 * Created by yzd on 2021/2/1
 */
public class LRUCacheTest {

    @Test
    public void testCache() {
        LRUCache cache = new LRUCache(2);
        cache.put(1, 1);
        cache.put(2, 2);
        cache.put(3, 3);
        System.out.println(cache.get(1));
    }
}