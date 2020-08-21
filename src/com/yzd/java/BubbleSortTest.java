package com.yzd.java;
import org.junit.Test;

import java.util.Arrays;


/**
 * Created by yzd on 2019/12/25
 */

public class BubbleSortTest {
    @Test
    public void bubbleSort() {
        Integer arrays[] = {2,9,7,5,3,6};
        System.out.println("冒泡排序前：");
        Arrays.asList(arrays).stream()
                .forEach(x -> System.out.print(x + " "));
        BubbleSort bubbleSort = new BubbleSort();
        Integer[] result = bubbleSort.bubbleSort(arrays);
        System.out.println("\n冒泡排序后：");
        Arrays.asList(result).stream()
                .forEach(x -> System.out.print(x + " "));
    }
}
