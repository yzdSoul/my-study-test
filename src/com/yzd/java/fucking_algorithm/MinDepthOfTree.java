package com.yzd.java.fucking_algorithm;

import com.yzd.java.leetcode.data_structure.tree.TreeNode;

import java.util.LinkedList;
import java.util.Queue;

/**
 * Created by yzd on 2020/9/18
 */
public class MinDepthOfTree {
    /**
     * 111. 二叉树的最小深度
     * 给定一个二叉树，找出其最小深度。
     *
     * 最小深度是从根节点到最近叶子节点的最短路径上的节点数量。
     *
     * 说明: 叶子节点是指没有子节点的节点。
     *
     * 示例:
     *
     * 给定二叉树 [3,9,20,null,null,15,7],
     *
     *       3
     *      / \
     *     9  20
     *       /  \
     *      15   7
     *      最小深度为2
     */
    public int minDepth(TreeNode root) {
        if (root == null) return 0;
        Queue<TreeNode> q = new LinkedList<>();
        q.offer(root);
        //root 本身就是一层，depth 初始化为 1
        int depth = 1;
        while (!q.isEmpty()) {
            int size = q.size();
            //将当前队列中的所有节点向四周扩散
            for (int i = 0; i < size; i++) {
                TreeNode cur = q.poll();
                //判断是否到达终点
                if (cur.left == null && cur.right == null) {
                    return depth;
                }
                //将 cur 的相邻节点加入队列
                if (cur.left != null) {
                    q.offer(cur.left);
                }
                if (cur.right != null) {
                    q.offer(cur.right);
                }
            }
            depth++;
        }
        return depth;
    }
}
