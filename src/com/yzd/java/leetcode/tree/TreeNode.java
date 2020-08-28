package com.yzd.java.leetcode.tree;

/**
 * Created by yzd on 2020/8/28
 */
class TreeNode {
    private int val;
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
