package com.yzd.java.onjava8;

import java.util.Random;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Created by yzd on 2020/9/21
 */
public class Generator implements Supplier<String> {
    Random random = new Random(47);

    char[] letters = "ABCDEFGHIJKLMNOPQRSTUVWXYZ".toCharArray();

    @Override
    public String get() {
        return "" + letters[random.nextInt(letters.length)];
    }

    public static void main(String[] args){
        String word = Stream.generate(new Generator())
                            .limit(30)
                            .collect(Collectors.joining());
        System.out.println(word);

        Stream.generate(() -> "duplicate")
                .limit(3)
                .forEach(System.out::println);
    }
}
