package com.yzd.java.kuangstudy.Thread;

/**
 * Created by yzd on 2021/2/18
 */


//真实角色和代理角色都要实现同一个接口
//代理对象代理真实角色
    //好处：代理对象可以做很多真实对象做不了的事情
    //真实对象专注做自己的事情
public class StaticProxy {

    public static void main(String[] args) {
        new Thread(()-> System.out.println("marry you")).start();

        new WeddingCompany(new You()).HappyMarry();
    }
}

interface Marry {
    void HappyMarry();
}

//真实角色
class You implements Marry {

    @Override
    public void HappyMarry() {
        System.out.println("happy");
    }
}

//代理角色
class WeddingCompany implements Marry {

    private Marry target;

    public WeddingCompany(Marry target) {
        this.target = target;
    }

    @Override
    public void HappyMarry() {
        before();
        this.target.HappyMarry();
        after();
    }

    private void before() {
        System.out.println("准备");
    }

    private void after() {
        System.out.println("付尾款");
    }
}

