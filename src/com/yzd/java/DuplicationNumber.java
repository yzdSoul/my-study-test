package com.yzd.java;

/**
 * Created by yzd on 2020/5/20
 */
public class DuplicationNumber {
    //数组中重复的数字

    /**
     *  在一个长度为 n 的数组里的所有数字都在 0 到 n-1 的范围内。数组中某些数字是重复的，
     *  但不知道有几个数字是重复的，也不知道每个数字重复几次。请找出数组中任意一个重复的数字。
     */
    public int duplicateInArray(int[] nums){
        int length = nums.length;
        if (nums == null || length <= 0){
            return -1;
        }
        for(int i = 0; i < length; i++){
            while (nums[i] != i){
                if (nums[i] == nums[nums[i]]){
                    return nums[i];
                }
                swap(nums,i,nums[i]);
            }
        }
        return -1;
    }

    private void swap(int[] nums,int i,int j){
        int t = nums[i];
        nums[i] = nums[j];
        nums[j] = t;
    }
}
