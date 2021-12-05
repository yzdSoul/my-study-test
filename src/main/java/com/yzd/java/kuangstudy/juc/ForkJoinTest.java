package com.yzd.java.kuangstudy.juc;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ForkJoinTask;
import java.util.concurrent.RecursiveTask;
import java.util.stream.LongStream;

/**
 * Created by yzd on 2021/2/24
 */
//如何使用forkJoin
public class ForkJoinTest extends RecursiveTask<Long> {
    private Long start;
    private Long end;
    private Long temp = 10000L;

    public ForkJoinTest(Long start, Long end) {
        this.start = start;
        this.end = end;
    }

    @Override
    protected Long compute() {
        if ((end - start) < temp) {
            Long sum = 0L;
            for (Long i = start; i <= end; i++) {
                sum += i;
            }
            return sum;
        } else {
            long middle = (start + end) / 2;
            ForkJoinTest task1 = new ForkJoinTest(start, middle);
            task1.fork();
            ForkJoinTest task2 = new ForkJoinTest(middle+1, end);
            task2.fork();
            return task1.join() + task2.join();
        }
    }



    public static void main(String[] args) throws ExecutionException, InterruptedException {
        long start = System.currentTimeMillis();
//        Long sum = 0L;
//        直接累加 10738
//        for (Long i = 1L; i < 10_0000_0000L; i++) {
//            sum += i;
//        }

//       ForkJoin  7490
//        ForkJoinPool forkJoinPool = new ForkJoinPool();
//        ForkJoinTask<Long> task =  new ForkJoinTest(0L,10_0000_0000L);
//        ForkJoinTask<Long> submit = forkJoinPool.submit(task);
//        Long sum = submit.get();

//        Stream 流式计算1609
        long sum = LongStream.rangeClosed(0L, 10_0000_0000L).parallel().reduce(0, Long::sum);
        long end = System.currentTimeMillis();
        System.out.println("sum=" +sum+ "时间 " + (end - start));
    }
}
