package com.yzd.java.fucking_algorithm;

import java.util.Arrays;

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

/**
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
/**
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


}
