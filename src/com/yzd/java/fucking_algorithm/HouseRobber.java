package com.yzd.java.fucking_algorithm;

import com.yzd.java.leetcode.data_structure.tree.TreeNode;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by yzd on 2020/9/4
 */
public class HouseRobber {
//    输入：[1,2,3,1]
//    输出：4
//    解释：偷窃 1 号房屋 (金额 = 1) ，然后偷窃 3 号房屋 (金额 = 3)。
//    偷窃到的最高金额 = 1 + 3 = 4 。
//
//    来源：力扣（LeetCode）
//    链接：https://leetcode-cn.com/problems/house-robber

/*
    public int rob(int[] nums){
       return dp(nums, 0);
    }

    private int dp(int[] nums,int start){
        if (start>=nums.length) return 0;
        int res = Math.max(dp(nums,start + 1),
                nums[start] + dp(nums,start + 2) );
        return res;
    }
*/

/*
 * 有重叠子 加备忘录优化  自顶向下动态规划解法
    private int[] memo;

    public int rob(int[] nums){
        memo = new int[nums.length];
        Arrays.fill(memo, -1);
        return dp(nums, 0);
    }

    private int dp(int[] nums,int start){
        if (start>=nums.length) return 0;
        if (memo[start] != -1) return memo[start];
        int res = Math.max(dp(nums, start + 1), nums[start] + dp(nums, start + 2));
        memo[start] = res;
        return  res;
    }*/

/*  int[] nums = {2,4,3};
    public int rob(int[] nums){
        int n = nums.length;
        //dp[i] = x 表示：
        //从第i间房子开始抢劫，最多能抢到的钱为x
        int[] dp = new int[n + 2];
        for (int i = n - 1; i >= 0; i--) {
            dp[i] = Math.max(dp[i + 1], nums[i] + dp[i + 2]);
        }
        return dp[0];
    }*/

/*
    public int rob(int[] nums) {
        int n = nums.length;
        int dp_i = 0, dp_i_1 = 0, dp_i_2 = 0;
        for (int i = n - 1; i >= 0; i--) {
            dp_i = Math.max(dp_i_1, nums[i] + dp_i_2);
            dp_i_2 = dp_i_1;
            dp_i_1 = dp_i;
        }
        return dp_i;
    }*/

//    House Robber II

/*    public int rob(int[] nums){
        int n = nums.length;
        if (n == 1) return nums[0];
        return Math.max(robRange(nums, 0, n - 2), robRange(nums, 1, n - 1));
    }

    private int robRange(int[] nums, int start, int end) {
        int n = nums.length;
        int dp_i = 0, dp_i_1 = 0, dp_i_2 = 0;
        for (int i = end; i >= end; i--) {
            dp_i = Math.max(dp_i_1, dp_i_2 + nums[i]);
            dp_i_2 = dp_i_1;
            dp_i_1 = dp_i;
        }
        return  dp_i;
    }*/

//    House Robber III
//         3
//        / \
//       2   3
//        \   \
//         3   1

    Map<TreeNode, Integer> memo = new HashMap<>();
    public int rob(TreeNode root){
        if (root == null) return 0;
        if (memo.containsKey(root)) return memo.get(root);
        int do_it = root.val
                + (root.left == null ? 0 : rob(root.left.left))
                + (root.right == null ? 0 : rob(root.right.right));
        int not_do = rob(root.left) + rob(root.right);
        int res = Math.max(do_it, not_do);
        memo.put(root, res);
        return res;
    }
}
