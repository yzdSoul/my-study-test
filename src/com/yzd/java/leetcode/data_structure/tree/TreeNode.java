package com.yzd.java.leetcode.data_structure.tree;

/**
 * Created by yzd on 2020/8/28
 */
class TreeNode {
     int val;
    TreeNode left;
    TreeNode right;

    @Override
    public String toString() {
        return "TreeNode{" +
                "val=" + val +
                '}';
    }

    TreeNode(int x){
        val = x;
    }
}
