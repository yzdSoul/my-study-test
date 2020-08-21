package com.yzd.java;

/**
 * Created by yzd on 2019/12/24
 */
public class BubbleSort {
    public static Integer [] bubbleSort(Integer[] arr){
        if (arr.length<=1)
            return arr;
        for (int i = 0; i < arr.length; ++i) {
            // 提前退出冒泡循环的标志位,即一次比较中没有交换任何元素，这个数组就已经是有序的了
            boolean flag = false;
            for (int j = 0; j < arr.length - i - 1; ++j) {        //此处的 arr.length - i - 1就是每趟排序的减少
                // 数组下标又是从0开始的，i下标后面已经排序的个数就得多减1，总结就是i增多少，j的循环位置减多少
                if (arr[j] > arr[j + 1]) {        //前一位与后一位与前一位比较，如果前一位比后一位要大，那么交换
                    int temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;
                    flag = true;
                }
            }
            //没有数据交换，数组已经有序，退出排序
            if (!flag) break;
        }
        return arr;
    }

}
