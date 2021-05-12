package com.yzd.java.fucking_algorithm.DynamicProgramming;

/**
 * Created by yzd on 2020/9/9
 */
public class FibonacciNumber {
/*    F(0) = 0,   F(1) = 1
    F(N) = F(N - 1) + F(N - 2), 其中 N > 1.
   根据N 求 F(N)*/
// 暴力解法  问题个数 O(2^n) 时间O(1)  时间复杂度O(2^n)
//    public int fib(int n){
//        if (n == 1 || n== 2) return 1;
//        return fib(n - 1) + fib(n - 2);
//    }

/**  自顶向下 递归存在大量重复计算 加备忘录优化  优化后时间复杂度为O(n)
    public int fib(int n) {
        if (n < 1) return 0;
        int[] memo = new int[n + 1];
        return dp(memo, n);
    }
    private int dp(int[] memo,int n){
        if (n == 1 || n == 2) return 1;
        if (memo[n] != 0) return memo[n];
        memo[n] = dp(memo, n - 1) + dp(memo, n - 2);
        return memo[n];
    }
    */
//====================================
//   自底向上 当前状态只和前两个状态有关 【状态压缩】  进行优化后空间复杂度为O(1)
    public int fib(int n){
        if (n == 1 || n == 2) return 1;
        int prev = 1 ,curr = 1;
        for (int i = 3; i <= n; i++) {
            int sum = prev + curr;
            prev = curr;
            curr = sum;
        }
        return curr;
    }

}
