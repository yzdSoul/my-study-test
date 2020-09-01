package com.yzd.java.leetcode.data_structure.tree;


/**
 * Created by yzd on 2020/8/28
 */
public class TestTree {
//    104. Maximum Depth of Binary Tree (Easy)  树的高度

    public int treeDepth(TreeNode root) {
        if (root == null) return 0;
        return Math.max(treeDepth(root.left), treeDepth(root.right)) + 1;
    }

//     平衡树
//    110. Balanced Binary Tree (Easy)        3
//                                           / \
//                                          9  20
//                                            /  \
//                                           15   7

    private boolean result = true;

    public boolean isBalanced(TreeNode root) {
        maxDepth(root);
        return result;
    }

//     平衡树左右子节点高度差都小于1
    public int maxDepth(TreeNode root) {
        if (root == null) return 0;
        int l = maxDepth(root.left);
        int r = maxDepth(root.right);
        if (Math.abs(l - r) > 1) result = false;
        return 1 + Math.max(l, r);
    }

//    543. Diameter of Binary Tree (Easy)   两节点的最长路径
//    Input:
//
//             1
//            / \
//           2  3
//          / \
//         4   5
//
//    Return 3, which is the length of the path [4,2,1,3] or [5,2,1,3].

    private int max = 0;

    public int diameterOfBinaryTree(TreeNode root) {
        depth(root);
        return max;
    }

    private int depth(TreeNode root) {
        if (root == null) return 0;
        int leftDepth = depth(root.left);
        int rightDepth = depth(root.right);
        max = Math.max(max, leftDepth + rightDepth);
        return Math.max(leftDepth , rightDepth) + 1;
    }

//    226. Invert Binary Tree (Easy) 反转树

    public TreeNode invertTree(TreeNode root) {
        if (root == null) return null;
        TreeNode left = root.left;
        root.left = invertTree(root.right);
        root.right = invertTree(left);
        return root;
    }

//    617. Merge Two Binary Trees (Easy)
//    Input:
//    Tree 1                     Tree 2
//            1                         2
//            / \                       / \
//           3   2                     1   3
//          /                           \   \
//         5                             4   7
//
//    Output:
//            3
//            / \
//           4   5
//          / \   \
//         5   4   7

    public TreeNode mergeTrees(TreeNode t1, TreeNode t2) {
        if (t1 == null && t2 == null) return null;
        if (t1 == null ) return t2;
        if (t2 == null ) return t1;
        TreeNode root = new TreeNode(t1.val + t2.val);
        root.left = mergeTrees(t1.left,t2.left);
        root.right = mergeTrees(t2.right,t1.right);
        return root;
    }

//    Leetcdoe : 112. Path Sum (Easy)  判断路径和是否等于一个数

//    Given the below binary tree and sum = 22,
//
//                 5
//                / \
//               4   8
//              /   / \
//             11  13  4
//             /  \      \
//            7    2      1
//return true, as there exist a root-to-leaf path 5->4->11->2 which sum is 22.

    public boolean hasPathSum(TreeNode root, int sum){
        if (root == null) return false;
        if (root.left == null && root.right == null && root.val == sum) return true;
        return hasPathSum(root.left, sum - root.val) || hasPathSum(root.right, sum - root.val);
    }

//    437. Path Sum III (Easy)  统计路径和等于一个数的路径数量
//
//    root = [10,5,-3,3,2,null,11,3,-2,null,1], sum = 8
//
//                10
//               /  \
//              5   -3
//             / \    \
//            3   2   11
//           / \   \
//          3  -2   1
//
//    Return 3. The paths that sum to 8 are:
//
//            1.  5 -> 3
//            2.  5 -> 2 -> 1
//            3. -3 -> 11
//路径不一定以 root 开头，也不一定以 leaf 结尾，但是必须连续。

    public int pathSum(TreeNode root , int sum){
        if (root == null) return 0;
        return pathSumStartWithRoot(root,sum) + pathSum(root.left,sum) + pathSum(root.right,sum);
    }
    private int pathSumStartWithRoot(TreeNode root,int sum){
        if (root == null) return 0;
        int ret = 0;
        if (root.val == sum) ret++;
        ret += pathSumStartWithRoot(root.left, sum - root.val) + pathSumStartWithRoot(root.right, sum - root.val);
        return  ret;
    }

//    572. Subtree of Another Tree (Easy)  子树
//   Given tree s:
//                3
//               / \
//              4   5
//             / \
//            1   2
//
//    Given tree t:
//              4
//             / \
//            1   2
//
//    Return true, because t has the same structure and node values with a subtree of s.
//
//    Given tree s:
//
//                 3
//                / \
//               4   5
//              / \
//             1   2
//            /
//           0
//
//    Given tree t:
//               4
//              / \
//             1   2
//
//    Return false.

     public boolean isSubtree(TreeNode s, TreeNode t){
        if (s == null) return false;
        return isSubtreeWithRoot(s, t) || isSubtree(s.left,t) || isSubtree(s.right,t);
     }

     private boolean isSubtreeWithRoot(TreeNode s,TreeNode t){
        if (t == null && s == null ) return true;
        if (t == null || s == null ) return false;
        if (t.val != s.val) return false;
        return isSubtreeWithRoot(s.left,t.left) && isSubtreeWithRoot(s.right,t.right);
     }
}
