package com.yzd.java.kuangstudy.juc;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

/**
 * Created by yzd on 2021/2/24
 */
//流式计算
public class StreamTest {
    public static void main(String[] args) {
        User u1 = new User(1, "a", 12);
        User u2 = new User(2, "b", 13);
        User u3 = new User(2, "c", 14);
        User u4 = new User(4, "d", 15);
        User u5 = new User(5, "e", 16);
        List<User> list = Arrays.asList(u1, u2, u3, u4, u5);
        list.stream()
                .filter(u -> u.getId() % 2 == 0)
                .filter(u -> u.getAge() > 13)
                .map(u -> u.getName().toUpperCase())
                .sorted(Comparator.reverseOrder())
                .limit(1)
                .forEach(System.out::println);

    }
}

class User {
   private int id;
   private String name;
   private int age;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public User(int id, String name, int age) {
        this.id = id;
        this.name = name;
        this.age = age;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", age=" + age +
                '}';
    }
}
