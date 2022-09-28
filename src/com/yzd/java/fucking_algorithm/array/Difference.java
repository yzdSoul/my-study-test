package com.yzd.java.fucking_algorithm.array;

/**
 * Created by yzd on 2021/12/8
 */
//差分数组工具类
public class Difference {
    // 分差数组
    private int[] diff;

    //输入一个初始数组，区间操作将在这个数组上进行
    public Difference(int[] nums){
        int n = nums.length;
        assert n > 0;
        diff = new int[n];
        diff[0] = nums[0];
        for (int i = 1; i < n; i++) {
            diff[i] = nums[i] - nums[i -1];
        }
    }

    // 给闭区间 [i,j] 增加 val (可以是负数)
    public void increment(int i, int j, int val) {
        diff[i] += val;
        if (j + 1 < diff.length){
            diff[j + 1] -= val;
        }
    }
    // 返回结果数组
    public int[] result(){
        int [] result = new int[diff.length];
        //根据差分数组构造结果
        result[0] = diff[0];
        for (int i = 1; i < diff.length; i++) {
            result[i] = result[i - 1] + diff[i];
        }
        return result;
    }
}
