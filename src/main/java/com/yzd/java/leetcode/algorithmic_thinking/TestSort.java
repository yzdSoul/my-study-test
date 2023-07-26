package com.yzd.java.leetcode.algorithmic_thinking;

import java.util.Arrays;
import java.util.PriorityQueue;

/**
 * Created by yzd on 2020/6/12
 */
public class TestSort {

    //排序

    /**
     * 215. Kth Largest Element in an Array (Medium)
     *
     * Input: [3,2,1,5,6,4] and k = 2
     * Output: 5
     *
     * 题目描述：找到倒数第 k 个的元素。
     *
     */
    //排序 ：时间复杂度 O(NlogN)，空间复杂度 O(1)
    public int findKthlargest1(int[] nums,int k){
        Arrays.sort(nums);
        return nums[nums.length - k];
    }

    //堆 ：时间复杂度 O(NlogK)，空间复杂度 O(K)。
    public int findKthlargest2(int[] nums,int k){
        PriorityQueue<Integer> pq = new PriorityQueue<>();
        for (int val : nums) {
            pq.add(val);
            if (pq.size() > k)
                pq.poll();
        }
        return pq.peek();
    }

    //快速选择 ：时间复杂度 O(N)，空间复杂度 O(1)
    public int findKthlargest3(int[] nums,int k){
        k = nums.length - k;
        int l = 0,h = nums.length - 1;
        while (l < h ){
            int j = partition(nums, l, h);
            if (j == k){
                break;
            }   else if (j < k){
                l = j + 1;
            }   else {
                h = j - 1;
            }
        }
        return nums[k];
    }

    private int partition(int[] a, int l, int h) {
        int i = l, j = h + 1;
        while (true){
            while (a[++i] < a[l] && i < h);
            while (a[--j] > a[l] && j > l);
            if (i >= j){
                break;
            }
            swap(a, i, j);
        }
        swap(a, l, j);
        return j;
    }

    private void swap(int[] a, int i, int j) {
        int t = a[i];
        a[i] = a[j];
        a[j] = t;
    }
}
