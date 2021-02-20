package com.yzd.java.kuangstudy.juc;

/**
 * Created by yzd on 2021/2/20
 */
public class Testpc {
    public static void main(String[] args) {
        Data data = new Data();
        new Thread(()->{
            for (int i = 0; i < 20; i++) {
                data.increment();
            }
        },"A").start();

        new Thread(()->{
            for (int i = 0; i < 20; i++) {
                data.decrement();
            }
        },"B").start();

        new Thread(()->{
            for (int i = 0; i < 20; i++) {
                data.increment();
            }
        },"C").start();

        new Thread(()->{
            for (int i = 0; i < 20; i++) {
                data.decrement();
            }
        },"D").start();
    }
}
//判断等待 业务 通知
// A B C D 四个线程  if 改为 while 避免虚假唤醒
class Data {
    private int number;

    public synchronized void increment() {
        //if
        while (number != 0) {
            try {
                this.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        number++;
        System.out.println(Thread.currentThread().getName()+"->"+number);
        this.notifyAll();
    }

    public synchronized void decrement() {
        //if
        while (number == 0) {
            try {
                this.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        number--;
        System.out.println(Thread.currentThread().getName()+"->"+number);
        this.notifyAll();
    }
}