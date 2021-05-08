package com.yzd.java.leetcode.string;


/**
 * Created by yzd on 2021/5/6
 */
public class RotateString {
    public boolean rotateString(String A, String B) {
        if (A.equals(B)) return true;
        if (A.equals("") || B.equals("")) return false;
        int count = A.length()+1;
        while (count > 0) {
            if (A.equals(B)){
                return true;
            }
            A = A.substring(1) + A.substring(0,1);
            count--;
        }
        return false;
    }

    public static void main(String[] args) throws InterruptedException {
        String A ="abcde";
        String B = "abced";
        RotateString rotateString = new RotateString();
        boolean b = rotateString.rotateString(A, B);
        System.out.println(b);
    }
}
