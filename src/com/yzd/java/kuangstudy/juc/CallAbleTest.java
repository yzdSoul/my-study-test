package com.yzd.java.kuangstudy.juc;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

/**
 * Created by yzd on 2021/2/22
 */
public class CallAbleTest {
    public static void main(String[] args) {
//        new Thread(new Runnable()).start();
//        new Thread(new FutureTask<V>()).start();

        MyThread myThread = new MyThread();
        FutureTask futureTask = new FutureTask(myThread);//适配类

        new Thread(futureTask, "A").start();
        new Thread(futureTask, "B").start();
        Object o = null;
        try {
            o = futureTask.get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        System.out.println(o);

    }
}

class MyThread implements Callable<String> {

    @Override
    public String call() {
        return "callable test";
    }
}

// 存在缓存 提高效率
// 返回结果会阻塞
