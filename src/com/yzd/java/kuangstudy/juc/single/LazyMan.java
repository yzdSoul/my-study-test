package com.yzd.java.kuangstudy.juc.single;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

/**
 * Created by yzd on 2021/2/25
 */
//懒汉式单例
public class LazyMan {
    private static boolean key = false;

    private LazyMan() {
        synchronized (LazyMan.class) {
            if (!key) {
                key = true;
            } else {
                throw new RuntimeException("不要试图用反射破坏异常");
            }
        }
        System.out.println(Thread.currentThread().getName());
    }

    private volatile static LazyMan LAZY_MAN;
    //双重检测模式 DCL 懒汉式
    public static LazyMan getInstance() {
        if (LAZY_MAN == null) {
            synchronized (LazyMan.class) {
                if (LAZY_MAN == null) {
                    LAZY_MAN = new LazyMan();//不是一个原子操作
                    /*
                    * 1，分配空间
                    * 2，执行构造方法，初始化对象
                    * 3，把这个对象指向这个空间
                    *
                    * 123
                    * 132 A
                    *     B 此时LazyMan还没有完成构造
                    *
                    * */
                }
            }
        }
        return LAZY_MAN;
    }


    public static void main(String[] args) throws NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException {
        LazyMan lazyMan = LazyMan.getInstance();
        Constructor<LazyMan> constructor = LazyMan.class.getDeclaredConstructor(null);
        constructor.setAccessible(true);
        LazyMan lazyMan1 = constructor.newInstance();
        System.out.println(lazyMan.equals(lazyMan1));
    }
}
