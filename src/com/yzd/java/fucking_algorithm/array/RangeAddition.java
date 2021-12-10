package com.yzd.java.fucking_algorithm.array;

/**
 * Created by yzd on 2021/12/8
 */

import java.util.Arrays;

/**
 * 题目描述:
 *
 * 假设你有一个长度为 n 的数组
 *
 * ，初始情况下所有的数字均为 0，你将会被给出 k 个更新的操作。
 *
 * 其中，每个操作会被表示为一个三元组：[startIndex, endIndex, inc]，你需要将子数组 A[startIndex ... endIndex]（包括 startIndex 和 endIndex）增加 inc。
 *
 * 请你返回 k 次操作后的数组。
 *
 *
 * 输入: length = 5, updates = [[1,3,2],[2,4,3],[0,2,-2]]
 * 输出: [-2,0,3,5,3]
 *
 *
 * 初始状态:
 * [0,0,0,0,0]
 *
 * 进行了操作 [1,3,2] 后的状态:
 * [0,2,2,2,0]
 *
 * 进行了操作 [2,4,3] 后的状态:
 * [0,2,5,5,3]
 *
 * 进行了操作 [0,2,-2] 后的状态:
 * [-2,0,3,5,3]
 *
 */
public class RangeAddition {
    public int[] getModifiedArray(int length, int[][] updates){
        //nums 初始化为全 0
        int[] nums = new int[length];
        // 构造差分解法
        Difference df = new Difference(nums);

        for (int[] update : updates
        ) {
            int i = update[0];
            int j = update[1];
            int val = update[2];
            df.increment(i, j, val);
        }
        return df.result();
    }


    public static void main(String[] args) {
        RangeAddition rangeAddition = new RangeAddition();
        int length = 5;
        int [][] updates = new int[][]{{1,3,2},{2,4,3},{0,2,-2}};
        int[] modifiedArray = rangeAddition.getModifiedArray(5, updates);
        System.out.println(Arrays.toString(modifiedArray));
    }
}