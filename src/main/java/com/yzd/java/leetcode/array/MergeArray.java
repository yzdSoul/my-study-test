package com.yzd.java.leetcode.array;

/**
 * Created by yzd on 2021/5/8
 */

import java.util.Arrays;

/**
 * 给你两个有序整数数组 nums1 和 nums2，请你将 nums2 合并到 nums1 中，使 nums1 成为一个有序数组。
 *
 * 初始化 nums1 和 nums2 的元素数量分别为 m 和 n 。你可以假设 nums1 的空间大小等于 m + n，这样它就有足够的空间保存来自 nums2 的元素。
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/merge-sorted-array
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */

public class MergeArray {
    /** 一 直接合并后排序
     * 时间复杂度  O((m+n)log(m+n))
     * 空间复杂度  O(log(m+n))
     */
    public static int[] merge(int[] nums1, int m, int[] nums2, int n) {
        for (int i = 0; i < n; i++) {
            nums1[m + i] = nums2[i];
        }
        Arrays.sort(nums1);
        return nums1;
    }
    /**
     *  双指针
     *  时间复杂度  O(m + n)
     *  空间负责度  O(m + n)
     */
    public static int[] merge2(int[] nums1, int m, int[] nums2, int n) {
        int p1 = 0, p2 = 0;
        int sorted[] = new int[m + n];
        int cur;

        while (p1 < m || p2 < n) {
            if (p1 == m) {
                cur = nums2[p2++];
            } else if (p2 == n) {
                cur = nums1[p1++];
            } else if (nums1[p1] < nums2[p2]) {
                cur = nums1[p1++];
            } else {
                cur = nums2[p2++];
            }
            sorted[p1 + p2 - 1] = cur;
        }

        return sorted;
    }

//面试题
//   已知 数组a , b为有序数组 其中没有为0的元素  将数组合并为有序数组c
    public static int[] merge3(int[] a,int[] b){
        int m = a.length, n = b.length;
        int[] c = new int[m + n];
        int p1 = 0, p2 = 0, cur;
        while (p1 < m || p2 < n) {
            if (p1 == m){
                cur = b[p2++];
            } else if (p2 == n) {
                cur = a[p1++];
            } else if (a[p1] < b[p2]) {
                cur = a[p1++];
            } else {
                cur = b[p2++];
            }
            //数组下标从0开始
            c[p1 + p2 - 1] = cur;
        }
        return c;
    }



    public static void main(String[] args) {
        int[] nums1 = new int[]{1,2,3,4,5,6},nums2 = new int[]{2,5,6};
        int m = 3, n = 3;
//        int[] merge = MergeArray.merge2(nums1, m, nums2, n);
        int[] merge = MergeArray.merge3(nums1,nums2);
        System.out.println(Arrays.toString(merge));
    }
}
