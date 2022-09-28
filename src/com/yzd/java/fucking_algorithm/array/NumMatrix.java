package com.yzd.java.fucking_algorithm.array;

/**
 * Created by yzd on 2021/12/7
 *
 * https://leetcode-cn.com/problems/range-sum-query-2d-immutable/
 *
 * 304. 二维区域和检索 - 矩阵不可变
 *
 * 前缀和
 */
public class NumMatrix {
    private int [][] preSum;

    public NumMatrix(int[][] matrix) {
        int m = matrix.length, n = matrix[0].length;
        if (m == 0 || n == 0) return;
        //构造前缀和矩阵
        preSum = new int[m + 1][n + 1];
        for (int i = 1; i <=m ; i++) {
            for (int j = 1; j <=n ; j++) {
                //计算每个矩阵 [0,0,i,j] 的元素和
                preSum[i][j] = preSum[i - 1][j] + preSum[i][j - 1] + matrix[i - 1][j - 1] - preSum[i - 1][j - 1];
            }
        }
    }

    public int sumRegion(int x1, int y1, int x2, int y2) {
        //目标矩阵之和由四个相邻矩阵运算获得
        return preSum[x2 + 1][y2 + 1] - preSum[x1][y2 + 1] - preSum[x2 + 1][y1] + preSum[x1][y1];
    }

}
