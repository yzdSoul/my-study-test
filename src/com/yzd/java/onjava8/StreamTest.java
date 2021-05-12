package com.yzd.java.onjava8;
import com.yzd.java.Peopel;
import org.junit.Test;
import java.util.*;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import static java.util.stream.IntStream.range;

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
    public static <T> void show(Stream<T> stream){
        stream.limit(4).forEach(System.out::println);
        System.out.println("+++++++++++");
    }
    @Test
    public void randomGenerators() {
        Random rand = new Random(47);
        show(rand.ints().boxed());
        show(rand.longs().boxed());
        show(rand.doubles().boxed());
        //控制上限和下限:
        show(rand.ints(10, 20).boxed());
        show(rand.longs(50, 100).boxed());
        show(rand.doubles(20, 30).boxed());
        //控制流的大小:
        show(rand.ints(2).boxed());
        show(rand.longs(2).boxed());
        show(rand.doubles(2).boxed());
        //控制流的大小和界限:
        show(rand.ints(3, 3, 9).boxed());
        show(rand.longs(3, 12, 22).boxed());
        show(rand.doubles(3, 11.5, 12.3).boxed());
    }

    @Test
    public void Ranges(){
        //传统方法
        int result = 0;
        for (int i = 10; i < 20; i++) {
            result += i;
        }
        System.out.println(result);
        // for-in 循环:
        result = 0;
        for (int i : range(10, 20).toArray())
            result += i;
        System.out.println(result);
        //使用流
        System.out.println(range(10, 20).sum());

    }


    public void repeat(int n, Runnable action) {
        range(0, n).forEach(i -> action.run());
    }

    @Test
    public void Looping(){
        repeat(3,() -> System.out.println("Looping!"));
    }

    @Test
    public void arrayStreams(){
        Arrays.stream(new double[]{3.14159, 2.718, 1.618})
                .forEach(n -> System.out.format("%f ", n));
        System.out.println();

        Arrays.stream(new int[]{1, 3, 5, 7})
                .forEach(n -> System.out.format("%d ", n));
        System.out.println();

        Arrays.stream(new long[]{11, 22, 44, 55})
                .forEach(n -> System.out.format("%d ", n));

        System.out.println();
        //选择一个子域：
        Arrays.stream(new int[]{1, 3, 5, 6, 7, 8, 43, 56}, 3, 6)
                .forEach(n -> System.out.printf("%d ", n));
    }

    @Test
    public void streamOfRandoms(){
        Random random = new Random(48);
        Stream.of(1, 2, 3, 4, 5)
                .flatMapToInt(i -> IntStream.concat(
                        random.ints(0, 100)
                                .limit(i), IntStream.of(-1)))
                        .forEach(n -> System.out.format("%d ",n));
    }

    @Test
    public void optionalsFromEmptyStreams(){
        System.out.println(Stream.<String>empty().findFirst());
        System.out.println(Stream.<String>empty().findAny());
        System.out.println(Stream.<String>empty().max(String.CASE_INSENSITIVE_ORDER));
        System.out.println(Stream.<String>empty().min(String.CASE_INSENSITIVE_ORDER));
        System.out.println(Stream.<String>empty().reduce((s1, s2) -> s1 + s2));
        System.out.println(IntStream.empty().average());
    }
}
