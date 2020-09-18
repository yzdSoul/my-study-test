package com.yzd.java.onjava8;

import com.yzd.java.BubbleSort;
import com.yzd.java.Peopel;
import org.junit.Test;

import java.util.*;
import java.util.stream.Stream;

/**
 * Created by yzd on 2020/9/18
 */
public class StreamTest {
    @Test
    public void Randoms(){
        new Random(45)
                .ints(5,20)
                .distinct()
                .limit(7)
                .sorted()
                .forEach(System.out::println);
    }

    @Test
    public void streamOf(){
        Stream.of(new Peopel("张三",18),new Peopel("李四",19),new Peopel("小明",20))
                .forEach(System.out::println);
        Stream.of("It's a ","wonderful ","dya ","for ","pie! ")
                .forEach(System.out::print);
        System.out.println();
        Stream.of(3.1415926,2.6499,5.54560)
                .forEach(System.out::println);
    }

    @Test
    public void collectionToStream(){
        List<Peopel> peopels = Arrays.asList(new Peopel("张三", 18), new Peopel("李四", 19), new Peopel("小明", 20));
        System.out.println(peopels.stream()
                .mapToInt(p -> p.getAge())
                .sum());
        Set<String> set = new HashSet<>(Arrays.asList("It's a wonderful day for pie!".split(" ")));
        set.stream()
                .map(x -> x +" ")
                .forEach(System.out::print);
        System.out.println();

        Map<String, Double> map = new HashMap<>();
        map.put("pi", 3.1415);
        map.put("e", 2.718);
        map.put("phi", 1.618);
        map.entrySet().stream()
                .map(e -> e.getKey() + ": " + e.getValue())
                .forEach(System.out::println);
    }
}
