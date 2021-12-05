package com.yzd.java.onjava8;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.stream.Stream;

/**
 * Created by yzd on 2020/9/22
 */
public class FileToWordsBuilder {

    Stream.Builder<String> builder = Stream.builder();

    public FileToWordsBuilder(String filePath) throws Exception{
        Files.lines(Paths.get(filePath))
                .skip(1)
                .forEach(line ->{
                    for (String w : line.split("[ .?,]+"))
                        builder.add(w);
                });
    }

    Stream<String> stream(){
        return builder.build();
    }

    public static void main(String[] args) throws Exception {
        new FileToWordsBuilder("C:\\Users\\Administrator\\Desktop\\Cheese.dat")
                .stream()
                .limit(7)
                .map(w -> w + " ")
                .forEach(System.out::print);
    }
}
