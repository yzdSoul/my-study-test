package com.yzd.java.fucking_algorithm;

import java.util.Arrays;

/**
 * Created by yzd on 2021/3/2
 */
public class EatGrapes {
    public long solution(long a, long b, long c) {
        long[] nums = new long[]{a, b, c};
        Arrays.sort(nums);
        long sum = a + b + c;

        //能够组成三角形，可完全平分
        if (nums[0] + nums[1] > nums[2]) {
            return (sum + 2) / 3;
        }
        //不能组成三角形，平分最长边的情况
        if ((nums[0] + nums[1]) * 2 < nums[2]) {
            return (nums[2] + 1) / 2;
        }
        //不能组成三角形，但是依然可以完全平分
        return (sum + 2) / 3;
    }
}
