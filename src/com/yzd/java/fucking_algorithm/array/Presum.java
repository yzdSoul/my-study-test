package com.yzd.java.fucking_algorithm.array;

import java.util.HashMap;

/**
 * Created by yzd on 2021/12/1
 * https://mp.weixin.qq.com/s/4TxF0xVhlUO6v3teip9Jzg
 */
public class Presum {
//    求有多少个子数组的和为 k
    int subarraySum(int[] nums, int k) {
        int n = nums.length;
        int[] sum = new int[n + 1];
        sum[0] = 0;
        for (int i = 0; i < n; i++) {
            sum[i + 1] = sum[i] + nums[i];
        }
        int ans = 0;
        for (int i = 1; i <= n; i++) {
            for (int j = 0; j < i; j++) {
                //sum of nums[j..i-1]
                if (sum[i] - sum[j] == k) {
                    ans++;
                }
            }
        }
        return ans;
    }
    int subarraySum2(int[] nums, int k) {
        int n = nums.length;
        HashMap<Integer, Integer> preSum = new HashMap<>();
        preSum.put(0, 1);
        int ans = 0, sum0_i = 0;
        for (int i = 0; i < n; i++) {
            sum0_i += nums[i];
            int sum0_j = sum0_i - k;
            if (preSum.containsKey(sum0_j)) {
                ans += preSum.get(sum0_j);
            }
            preSum.put(sum0_i, preSum.getOrDefault(sum0_i, 0) + 1);
        }
        return ans;
    }

    public static void main(String[] args) {
        Presum presum = new Presum();
        int [] arr = new int[]{1,2,3,4,5,6,7,8,9};
        int k = 7;
        int sum = presum.subarraySum2(arr, k);
        System.out.println(sum);
    }
}
