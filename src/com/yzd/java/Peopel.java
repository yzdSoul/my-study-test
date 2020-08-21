package com.yzd.java;

import java.util.HashMap;

/**
 * Created by yzd on 2019/12/24
 */
public class Peopel {
    private String name;
    private int age;

    @Override
    public String toString() {
        return "Peopel{" +
                "name='" + name + '\'' +
                ", age=" + age +
                '}';
    }

    public Peopel(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }
    @Override
    public int hashCode(){
        return name.hashCode() * 20 * age;
    }
    @Override
    public boolean equals(Object obj){
        return this.name.equals(((Peopel) obj).name) && this.age == ((Peopel) obj).age;
    }

    public static void main(String[] args){
        Peopel p1 = new Peopel("雷军", 40);
        Peopel p2 = new Peopel("雷军", 40);
        HashMap<Peopel, Integer> hashMap = new HashMap<>();
        hashMap.put(p1,1);
        hashMap.put(p2,2);
        System.out.println(hashMap.get(new Peopel("雷军",40)));
        System.out.println(hashMap.size());
        System.out.println(p1.equals(p2));
    }
}
