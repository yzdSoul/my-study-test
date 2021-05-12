package com.yzd.java.fucking_algorithm;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by yzd on 2021/3/2
 */
public class PancakeSort {

    LinkedList<Integer> res = new LinkedList<>();

    List<Integer> pancakeSort(int[] cake) {
        sort(cake, cake.length);
        return res;
    }
    void sort(int[] cakes, int n) {
//        base case
        if (n == 1) return;
//      寻找最大饼的索引
        int maxCake = 0;
        int maxCakeIndex = 0;
        for (int i = 0; i < n; i++) {
            if (cakes[i] > maxCake) {
                maxCakeIndex = i;
                maxCake = cakes[i];
            }
        }
//      第一次翻转，将最大饼翻到最上面
            reverse(cakes, 0, maxCakeIndex);
            res.add(maxCakeIndex + 1);
//      第二次翻转，将最大饼翻到最下面
            reverse(cakes, 0, n - 1);
            res.add(n);


        sort(cakes,n -1);
    }

    void reverse(int[] arr, int i, int j) {
        while (i < j) {
            int temp = arr[i];
            arr[i] = arr[j];
            arr[j] = temp;
            i++; j--;
        }
    }


    public static void main(String[] args) {
        PancakeSort pancakeSort = new PancakeSort();
        int[] cakes = new int[]{3,2,4,1};
        List<Integer> integers = pancakeSort.pancakeSort(cakes);
        System.out.println(integers);
    }
}
