package com.yzd.java.kuangstudy.Thread;

/**
 * Created by yzd on 2021/2/18
 */
public class Testlambda1 {
    //3.静态内部类
    static class Like2 implements Ilike {

        @Override
        public void lambda() {
            System.out.println("I like lambda2");
        }
    }

    public static void main(String[] args) {
        Ilike like = new Like();
        like.lambda();

        like = new Like2();
        like.lambda();

        //4局部内部类
        class Like3 implements Ilike {

            @Override
            public void lambda() {
                System.out.println("I like lambda3");
            }
        }


        like = new Like3();
        like.lambda();


    //5.匿名内部类
        like = new Ilike() {
            @Override
            public void lambda() {
                System.out.println("I like lambda4");
            }
        };
        like.lambda();

    //6 用lambda简化
        like = () ->{
            System.out.println("i like lambda5");
        };
        like.lambda();
    }
}

//1.定义一个函数式接口
interface Ilike {
    void lambda();
}

//2.实现类
class Like implements Ilike {
    @Override
    public void lambda() {
        System.out.println("I like lambda");
    }
}
