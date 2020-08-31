package com.yzd.java.onjava8;

/**
 * Created by yzd on 2020/8/31
 */
public class SingletonPattern {
    public static void main(String[] args){
        Resource r = Singleton.getResource();
        System.out.println(r.getValue());
        Resource s = Singleton.getResource();
        s.setValue(9);
        System.out.println(r.getValue());

    }
}
