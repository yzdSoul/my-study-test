package com.yzd.java.kuangstudy.Thread;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by yzd on 2021/2/19
 */
public class TestPool {
    //创建服务 创建线程池
    public static void main(String[] args) {
        ExecutorService service = Executors.newFixedThreadPool(10);

        //执行
        service.execute(new MyThread());
        service.execute(new MyThread());
        service.execute(new MyThread());
        service.execute(new MyThread());


        //关闭链接
        service.shutdown();
    }
}

class MyThread implements Runnable {

    @Override
    public void run() {
            System.out.println(Thread.currentThread().getName());
    }
}