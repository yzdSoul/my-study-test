package com.yzd.java.leetcode.string;

/**
 * Created by yzd on 2021/5/11
 */

/**
 * https://leetcode-cn.com/problems/zuo-xuan-zhuan-zi-fu-chuan-lcof/
 *
 * 左旋转字符串
 *
 * 输入: s = "abcdefg", k = 2
 * 输出: "cdefgab"
 */

public class lcofString {
    public static String reverseLeftString(String s, int n){
        StringBuffer buffer = new StringBuffer();
        for (int i = n;i< s.length();i++){
            buffer.append(s.charAt(i));
        }
        for (int i = 0; i < n; i++) {
            buffer.append(s.charAt(i));
        }
        return buffer.toString();
    }


    public static void main(String[] args) {
        String s = lcofString.reverseLeftString("abcdefg", 2);
        System.out.println(s);
    }
}
