package com.yzd.java.fucking_algorithm;

import java.util.Arrays;

/**
 * Created by yzd on 2020/9/9
 */
public class CoinChange {

    /**
     * 给定不同面额的硬币 coins 和一个总金额 amount。
     * 编写一个函数来计算可以凑成总金额所需的最少的硬币个数。如果没有任何一种硬币组合能组成总金额，返回 -1。
     * 示例 1:
     * 输入: coins = [1, 2, 5], amount = 11
     * 输出: 3
     * 解释: 11 = 5 + 5 + 1
     */
    public int[] memo;
    public int coinChange(int[] coins, int amount){
        memo = new int[amount + 1];
        return dp(coins,amount);
    }
    private int dp(int[] coins, int amount) {
        if (amount == 0) return 0;
        if (amount < 0) return -1;
        if (memo[amount] !=0) return memo[amount];
        int res = Integer.MAX_VALUE;
        for (int coin : coins) {
            int subp = dp(coins, amount - coin);
            if (subp == -1) {
                continue;
            }
            res = Math.min(res, 1 + subp);
        }
        return memo[amount] = res == Integer.MAX_VALUE ? -1 : res;

    }

////迭代版
//    public int coinChange(int[] coins, int amount) {
//        int[] dp = new int[amount + 1];
//        for (int i = 0; i < dp.length; i++) {
//            dp[i] = i;
//        }
//        for (int i = 0; i < dp.length; i++) {
//            for (int coin : coins) {
//                if (i - coin < 0) {
//                    continue;
//                }
//                dp[i] = Math.min(dp[i], 1 + dp[i - coin]);
//            }
//        }
//        return (dp[amount] == amount + 1) ? -1 : dp[amount];
//    }
}
