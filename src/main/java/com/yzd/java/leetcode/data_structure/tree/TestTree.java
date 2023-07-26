package com.yzd.java.leetcode.data_structure.tree;


import java.util.*;

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
        TreeNode temp = root.left;
        root.left = root.right;
        root.right = temp;
        invertTree(root.left);
        invertTree(root.right);
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

//     101. Symmetric Tree (Easy)  树的对称
//                  1
//                 / \
//                2   2
//               / \ / \
//              3  4 4  3
    public boolean isSymmetric(TreeNode root){
        if (root == null) return true;
        return isSymmetric(root.left,root.left);
    }
    private boolean isSymmetric(TreeNode t1 , TreeNode t2){
        if (t1 == null && t2 == null) return true;
        if (t1 == null || t2 == null) return false;
        if (t1.val != t2.val) return false;
        return isSymmetric(t1.left, t2.right) && isSymmetric(t1.right, t2.left);
    }

//    111. Minimum Depth of Binary Tree (Easy)  树的根节点到叶子节点的最小路径长度

    public int minDepth(TreeNode root) {
        if (root == null) return 0;
        int left = minDepth(root.left);
        int right = minDepth(root.right);
        if (left == 0 || right == 0) return right + left + 1;
        return Math.min(left,right) + 1;
    }

//    404. Sum of Left Leaves (Easy)  统计左叶子节点的和

//            3
//           / \
//          9  20
//            /  \
//           15   7
//  There are two left leaves in the binary tree, with values 9 and 15 respectively. Return 24.
    public int sumOfLeftLeaves(TreeNode root){
        if (root == null) return 0;
        if (isLeaf(root.left)) return root.left.val + sumOfLeftLeaves(root.right);
        return sumOfLeftLeaves(root.left) + sumOfLeftLeaves(root.right);
    }


    private boolean isLeaf(TreeNode node){
        if (node == null) return false;
        return node.left == null && node.right == null;
    }

//    687. Longest Univalue Path (Easy)

//                 1
//                / \
//               4   5
//              / \   \
//             4   4   5
//
//    Output : 2
    private int path = 0;

    public int longestUnivaluePath(TreeNode root){
        dfs(root);
        return path;
    }

    private int dfs (TreeNode root){
        if (root == null) return 0;
        int left = dfs(root.left);
        int right = dfs(root.right);
        int leftPath = root.left != null && root.left.val == root.val ? left + 1 : 0;
        int rightPath = root.right != null && root.right.val == root.val ? right + 1 : 0;
        path = Math.max(path, leftPath + rightPath);
        return Math.max(leftPath,rightPath);
    }

//    337. House Robber III (Medium) 打家劫舍
//         3
//        / \
//       2   3
//        \   \
//         3   1
//    Maximum amount of money the thief can rob = 3 + 3 + 1 = 7.

    public int rob(TreeNode root){
        if (root == null) return 0;
        int val1 = root.val;
        if (root.left != null) val1 += rob(root.left.left) + rob(root.left.right);
        if (root.right != null) val1 += rob(root.right.left) + rob(root.right.right);
        int val2 = rob(root.left) + rob(root.right);
        return Math.max(val1, val2);
    }

//    671. Second Minimum Node In a Binary Tree (Easy)
//    Input:
//            2
//           / \
//          2   5
//         / \
//        5   7
//
//    Output: 5
    public int findSecondMinimumValue(TreeNode root){
        if (root == null ) return -1;
        if (root.left == null && root.right == null) return -1;
        int leftVal = root.left.val, rightVal = root.right.val;
        if (leftVal == root.val) leftVal = findSecondMinimumValue(root.left);
        if (rightVal == root.val) rightVal = findSecondMinimumValue(root.right);
        if (leftVal != -1 && rightVal != -1) return Math.min(leftVal, rightVal);
        if (leftVal != -1) return leftVal;
        return rightVal;
    }

//637. Average of Levels in Binary Tree (Easy)
    public List<Double> averageOfLevels(TreeNode root){
        List<Double> ret = new ArrayList<>();
        if (root == null) return ret;
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        while (!queue.isEmpty()) {
            int size = queue.size();
            double sum = 0;
            for (int i = 0; i < size; i++) {
                TreeNode node = queue.poll();
                sum += node.val;
                if (node.left != null) queue.add(node.left);
                if (node.right != null) queue.add(node.right);
            }
            ret.add(sum / size);
        }
        return ret;
    }

//   513. Find Bottom Left Tree Value (Easy)

    //    Input:
//
//            1
//           / \
//          2   3
//         /   / \
//        4   5   6
//       /
//      7
//
//    Output:
//    7
    public int findBottomLeftVale(TreeNode root) {
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        while (!queue.isEmpty()) {
            root = queue.poll();
            if (root.right != null) queue.add(root.right);
            if (root.left!= null) queue.add(root.left);
        }
        return root.val;
    }

//    116. 填充每个节点的下一个右侧节点指针
//    Definition for a Node.
    class Node {
        public int val;
        public Node left;
        public Node right;
        public Node next;

        public Node() {}

        public Node(int _val) {
            val = _val;
        }

        public Node(int _val, Node _left, Node _right, Node _next) {
            val = _val;
            left = _left;
            right = _right;
            next = _next;
        }
    }

    public Node connect(Node root) {
        if (root == null) {
            return null;
        }
        connectTwoNode(root.left, root.right);
        return root;
    }

    void connectTwoNode (Node node1, Node node2){
        if (node1 == null || node2 == null) {
            return;
        }
        node1.next = node2;
        connectTwoNode(node1.left, node1.right);
        connectTwoNode(node2.left, node2.right);
        connectTwoNode(node1.right, node2.left);
    }

//114. 二叉树展开为链表
//    https://leetcode-cn.com/problems/flatten-binary-tree-to-linked-list/
//    展开的节点顺序与前序遍历顺序相同

    public void flatten(TreeNode root) {
        if (root == null) {
            return;
        }
        flatten(root.left);
        flatten(root.right);
        // 后续遍历位置
        // 1、左右子树已经被拉平成一条链表
        TreeNode left = root.left;
        TreeNode right = root.right;
        // 2、将左子树作为右子树
        root.left = null;
        root.right = left;
        // 3、将原先的右子树接到当前右子树的末端
        TreeNode p = root;
        while (p.right != null) {
            p = p.right;
        }
        p.right = right;
    }

//    654. 最大二叉树
//    https://leetcode-cn.com/problems/maximum-binary-tree/

    public TreeNode constructMaximumBinaryTree(int[] nums) {
        return build(nums, 0, nums.length - 1);
    }

    TreeNode build(int[] nums, int lo, int hi) {
        if (lo > hi) {
            return null;
        }
        //找到数组中的最大值和对应的索引
        int index = -1, maxVal = Integer.MIN_VALUE;
        for (int i = lo; i <= hi; i++) {
            if (maxVal < nums[i]) {
                index = i;
                maxVal = nums[i];
            }
        }
        TreeNode root = new TreeNode(maxVal);
        // 递归调用构造左右子树
        root.left = build(nums, lo, index - 1);
        root.right = build(nums, index + 1, hi);

        return root;
    }


//    105. 从前序与中序遍历序列构造二叉树
//    https://leetcode-cn.com/problems/construct-binary-tree-from-preorder-and-inorder-traversal/

    public TreeNode buildTree(int[] preorder, int[] inorder) {
        return build(preorder, 0, preorder.length - 1,
                inorder, 0, inorder.length - 1);
    }

    TreeNode build(int[] preorder, int preStart, int preEnd,
                   int[] inorder, int inStart, int inEnd) {
        //root 节点对应的值就是前序遍历数组的第一个元素

        if (preStart > preEnd) {
            return null;
        }

        int rootVal = preorder[preStart];
        // rootVal 在中序遍历数组中的索引
        int index = 0;
        for (int i = inStart; i < inEnd; i++) {
            if (inorder[i] == rootVal) {
                index = i;
                break;
            }
        }
        int leftSize = index - inStart;

        TreeNode root = new TreeNode(rootVal);
        // 递归构造左右子树
        root.left = build(preorder, preStart + 1, preStart + leftSize,
                inorder, inStart, index - 1);
        root.right = build(preorder, preStart + leftSize + 1, preEnd,
                inorder, index + 1, inEnd);

        return root;
    }
}
