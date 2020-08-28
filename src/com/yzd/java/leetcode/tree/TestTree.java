package com.yzd.java.leetcode.tree;


/**
 * Created by yzd on 2020/8/28
 */
public class TestTree {
    //104. Maximum Depth of Binary Tree (Easy)

    public int maxDepth(TreeNode root){
        if (root == null) return 0;
        return Math.max(maxDepth(root.left),maxDepth(root.right)) + 1;
    }
}
