package com.yzd.java.kuangstudy.juc;

import java.util.concurrent.*;

/**
 * Created by yzd on 2021/2/23
 */
//Executors 工具类 三大方法
    //使用线程池创建线程
public class PoolTest1 {
    public static void main(String[] args) {
        ThreadPoolExecutor threadPool = new ThreadPoolExecutor(
                2,
                Runtime.getRuntime().availableProcessors(),//设置合适的最大线程数
                3,
                TimeUnit.SECONDS,
                new LinkedBlockingDeque<>(3),
                Executors.defaultThreadFactory(),
                new ThreadPoolExecutor.CallerRunsPolicy());//AbortPolicy() 拒绝策略  不处理并抛出异常
//        ExecutorService threadPool = Executors.newSingleThreadExecutor();//单个线程
//        ExecutorService threadPool = Executors.newFixedThreadPool(5);// 创建一个固定的线程池的大小
//        ExecutorService threadPool  =  Executors.newCachedThreadPool();// 可伸缩的
        try {
            for (int i = 1; i <= 9; i++) {
                threadPool.execute(() ->{
                    System.out.println(Thread.currentThread().getName() + " ok");
                });
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            threadPool.shutdown();
        }
    }
        

/**    public ThreadPoolExecutor(int corePoolSize, 核心线程数
                              int maximumPoolSize, 最大线程数
                              long keepAliveTime, 超时时间
                              TimeUnit unit, 超时时间单位
                              BlockingQueue<Runnable> workQueue, 阻塞队列
                              ThreadFactory threadFactory, 线程工厂：创建线程的
                              RejectedExecutionHandler handler) 拒绝策略
 {
        if (corePoolSize < 0 ||
                maximumPoolSize <= 0 ||
                maximumPoolSize < corePoolSize ||
                keepAliveTime < 0)
            throw new IllegalArgumentException();
        if (workQueue == null || threadFactory == null || handler == null)
            throw new NullPointerException();
        this.acc = System.getSecurityManager() == null ?
                null :
                AccessController.getContext();
        this.corePoolSize = corePoolSize;
        this.maximumPoolSize = maximumPoolSize;
        this.workQueue = workQueue;
        this.keepAliveTime = unit.toNanos(keepAliveTime);
        this.threadFactory = threadFactory;
        this.handler = handler;
    }*/
}
