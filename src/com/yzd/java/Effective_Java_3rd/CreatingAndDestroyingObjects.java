package com.yzd.java.Effective_Java_3rd;

/**
 * Created by yzd on 2020/7/14
 */
public class CreatingAndDestroyingObjects {


    public static Boolean valueOf(boolean b){
        return b ? Boolean.TRUE : Boolean.FALSE;
    }


    public static void main(String[] args) {
        long start,end;
        start = System.currentTimeMillis();
        valueOf(1 == 2);
        end = System.currentTimeMillis();

        System.out.println("start time:" + start+ "; end time:" + end+ "; Run Time:" + (end - start) + "(ms)");
    }
}
