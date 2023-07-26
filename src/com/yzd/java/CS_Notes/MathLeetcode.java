package com.yzd.java.CS_Notes;

/**
 * Created by yzd on 2020/12/16
 */
public class MathLeetcode {

    public int majorityElement(int nums[]) {
        int count = 0, majority = nums[0];
        for (int num : nums) {
            //如果投票数过半 cnt一定不为0
            majority = (count == 0) ? num : majority;
            count = (majority == num) ? count + 1 : count - 1;
        }
        return majority;
    }


}
