package com.yzd.java.leetcode.algorithmic_thinking;

/**
 * Created by yzd on 2020/6/5
 */
public class TestDynamic {

    //动态规划

    /**
     * 70. Climbing Stairs (Easy)
     *
     * Leetcode / 力扣
     *
     * 题目描述：有 N 阶楼梯，每次可以上一阶或者两阶，求有多少种上楼梯的方法。
     *
     * 定义一个数组 dp 存储上楼梯的方法数（为了方便讨论，数组下标从 1 开始），dp[i] 表示走到第 i 个楼梯的方法数目。
     *
     * 第 i 个楼梯可以从第 i-1 和 i-2 个楼梯再走一步到达，走到第 i 个楼梯的方法数为走到第 i-1 和第 i-2 个楼梯的方法数之和。
     *
     *          dp[i] = dp[i - 1] + dp[i - 2]
     *
     * 考虑到 dp[i] 只与 dp[i - 1] 和 dp[i - 2] 有关，因此可以只用两个变量来存储 dp[i - 1] 和 dp[i - 2]，使得原来的 O(N) 空间复杂度优化为 O(1) 复杂度。
     *
     */
    public int climbStairs(int n){
        if (n <= 2){
            return n;
        }
        int pre2 = 1,pre1 = 2;
        for (int i = 2;i < n;i++){
            int cur = pre1 + pre2;
            pre2 = pre1;
            pre1 = cur;
        }
        return pre1;
    }

    /**
     * 198. House Robber (Easy)
     *
     * Leetcode / 力扣
     *
     * 题目描述：抢劫一排住户，但是不能抢邻近的住户，求最大抢劫量。
     *
     * 定义 dp 数组用来存储最大的抢劫量，其中 dp[i] 表示抢到第 i 个住户时的最大抢劫量。
     *
     * 由于不能抢劫邻近住户，如果抢劫了第 i -1 个住户，那么就不能再抢劫第 i 个住户，所以
     *      dp[i] = max(dp[i - 2] + nums[i],dp[i - 1])
     */

    public int rob(int[] nums){
        int pre2 = 0,pre1 =0;
        for (int num : nums) {
            int cur = Math.max(pre2 + num, pre1);
            pre2 = pre1;
            pre1 = cur;
        }
        return pre1;
    }

    /**
     * 213. House Robber II (Medium)
     *
     */
    public int rob2(int[] nums){
        if (nums == null || nums.length == 0){
            return 0;
        }
        int n = nums.length;
        if (n == 1){
            return nums[0];
        }
        return Math.max(rob(nums,0,n - 2),rob(nums,1,n - 1));
    }

    private int rob(int[] nums,int first,int last){
        int pre2 = 0,pre1 = 0;
        for (int i = first; i <= last;i++){
            int cur = Math.max(pre1,pre2 + nums[i]);
            pre2 = pre1;
            pre1 = cur;
        }
        return pre1;
    }
}
