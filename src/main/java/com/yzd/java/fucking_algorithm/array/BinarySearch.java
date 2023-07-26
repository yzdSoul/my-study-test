package com.yzd.java.fucking_algorithm.array;

import java.util.Arrays;

/**
 * Created by yzd on 2021/12/16
 *
 *
 * 34. 在排序数组中查找元素的第一个和最后一个位置
 *
 * 给定一个按照升序排列的整数数组 nums，和一个目标值 target。找出给定目标值在数组中的开始位置和结束位置。
 *
 * 如果数组中不存在目标值 target，返回 [-1, -1]。
 *
 * 进阶：
 *
 *     你可以设计并实现时间复杂度为 O(log n) 的算法解决此问题吗？
 *
 *
 *
 * 示例 1：
 *
 * 输入：nums = [5,7,7,8,8,10], target = 8
 * 输出：[3,4]
 *
 * 示例 2：
 *
 * 输入：nums = [5,7,7,8,8,10], target = 6
 * 输出：[-1,-1]
 *
 * 示例 3：
 *
 * 输入：nums = [], target = 0
 * 输出：[-1,-1]
 *
 *
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/find-first-and-last-position-of-element-in-sorted-array
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class BinarySearch {
    public int[] searchRange(int[] nums, int target){
        return new int[]{left_bound(nums, target), right_bound(nums, target)};
    }
    int left_bound(int[] nums,int target){
        int left = 0, right = nums.length - 1;
        // 搜索区间为 [left,right]
        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (nums[mid] < target) {
                // 搜索区间变为 [mid + 1, right]
                left = mid + 1;
            } else if (nums[mid] > target) {
                // 搜索区间变为 [left , mid -1]
                right = mid - 1;
            } else if (nums[mid] == target) {
                right = mid - 1;
            }
        }
        //检测出界情况
        if (left >= nums.length || nums[left] != target) {
            return -1;
        }
        return left;
    }

    int right_bound(int[] nums, int target){
        if(nums.length == 0) return -1;
        int left = 0, right = nums.length;

        while (left < right) {
            int mid = left + (right - left) / 2;
            if (nums[mid] == target) {
                left = mid + 1;//注意不要直接返回
            } else if (nums[mid] < target) {
                left = mid + 1;
            } else if (nums[mid] > target) {
                right = mid;
            }
        }
        if(left == 0) return -1;
        return nums[left - 1] == target ? (left - 1) : -1;
    }
//    双指针 时间复杂度 O(n)
    public int[] getResult(int[] nums, int target){
        int[] result = new int[]{-1,-1};
        if (nums.length == 0) return result;
        int left = 0, right = nums.length - 1;
        // 搜索区间为 [left,right]
        for (int i = 0; i < nums.length; i++) {
            if (nums[left] == target) {
                result[0] = left;
            } else {
                left++;
            }
            if (nums[right] == target) {
                result[1] = right;
            } else {
                right--;
            }
            if (result[0] != -1 && result[1] != -1) {
                break;
            }
        }
        return result;
    }
//    二分查找 时间复杂度 O(logn)
    public int[] getResult2(int[] nums,int target){
        return new int[]{getLeft(nums,target),getRight(nums,target)};
    }
    public int getLeft(int[] nums,int target){
        if(nums.length == 0) return -1;
        int left = 0,right = nums.length - 1;
        while (left <= right){
            int mid = left + (right - left) / 2;
            if (nums[mid] < target){
                left = mid + 1;
            } else if (nums[mid] >target) {
                right = mid - 1;
            } else if (nums[mid] == target) {
                right = mid - 1;
            }
        }
        if (left >= nums.length || nums[left] != target){
            return -1;
        }
        return left;
    }
    public int getRight(int[] nums,int target){
        if(nums.length == 0) return -1;
        int left = 0, right = nums.length - 1;
        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (nums[mid] == target) {
                left = mid + 1;
            } else if (nums[mid] < target) {
                left = mid + 1;
            } else if (nums[mid] > target) {
                right = mid - 1;
            }
        }
        return nums[left - 1] == target ? (left - 1) : -1;
    }
    public static void main(String[] args) {
        int[] nums = new int[]{5,7,7,7,8,8,8,8,10};
        BinarySearch binarySearch = new BinarySearch();
//        int[] ints = binarySearch.getResult(nums, 8);
        int[] ints = binarySearch.getResult2(nums, 8);
//        int[] ints = binarySearch.searchRange(nums, 8);
        System.out.println(Arrays.toString(ints));
//        System.out.println(binarySearch.getLeft(nums,8));
    }
}
