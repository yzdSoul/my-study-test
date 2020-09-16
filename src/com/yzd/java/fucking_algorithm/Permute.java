package com.yzd.java.fucking_algorithm;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by yzd on 2020/9/15
 */
// 46.全排列
public class Permute {

    List<List<Integer>> res = new LinkedList<>();

    public List<List<Integer>> permute(int[] nums) {
        //记录路径
        LinkedList<Integer> track = new LinkedList<>();
        backTrack(nums,track);
        return res;
    }
    //路径记录在track中
    //选择列表：muns 中不存在track的元素
    //结束条件：nums 中的元素全在track中
    private void backTrack(int[] nums, LinkedList<Integer> track) {
        //触发结束条件
        if (track.size() == nums.length) {
            res.add(new LinkedList<>(track));
            return;
        }

        for (int i = 0; i < nums.length; i++) {
            //排除不合法的选择
            if (track.contains(nums[i])) {
                continue;
            }
            //做选择
            track.add(nums[i]);
            //下一层决策树
            backTrack(nums, track);
            // 取消选择
            track.removeLast();
        }
    }
}
