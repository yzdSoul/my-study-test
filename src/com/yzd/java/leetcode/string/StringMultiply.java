package com.yzd.java.leetcode.string;

import java.util.Arrays;

/**
 * Created by yzd on 2021/3/1
 */
public class StringMultiply {
    /*
    * 给定两个以字符串形式表示的非负整数 num1 和 num2，返回 num1 和 num2 的乘积，它们的乘积也表示为字符串形式。

示例 1:

输入: num1 = "2", num2 = "3"
输出: "6"

示例 2:

输入: num1 = "123", num2 = "456"
输出: "56088"

说明：

    num1 和 num2 的长度小于110。
    num1 和 num2 只包含数字 0-9。
    num1 和 num2 均不以零开头，除非是数字 0 本身。
    不能使用任何标准库的大数类型（比如 BigInteger）或直接将输入转换为整数来处理。

来源：力扣（LeetCode）
链接：https://leetcode-cn.com/problems/multiply-strings
著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
    * */
//     1 2 3
//       4 5
//      -----
//       1 5
//     1 0
//   0 5
//     1 2
//   0 8
// 0 4
// 0 5 5 3 5
    String sum = "";

    public String multiply(String num1, String num2) {
        int n = num1.length(), m = num2.length();
        int[] result = new int[n + m];
        char[] c1 = num1.toCharArray();
        char[] c2 = num2.toCharArray();
        for (int i = n - 1; i >= 0; i--) {
            for (int j = m - 1; j >= 0; j--) {
                int temp = (c1[i] - '0') * (c2[j] - '0');
                int p1 = i + j, p2 = i + j + 1;
                int sum = temp + result[p2];
                result[p2] = sum % 10;
                result[p1] += sum / 10;
            }
        }
        int i = 0;
        while (i < result.length && result[i] == 0) {
            i++;
            for (; i < result.length; i++) {
                sum += result[i];
            }
        }

        return sum;
    }

    public static void main(String[] args) {
        StringMultiply stringMultiply = new StringMultiply();
        String multiply = stringMultiply.multiply("123", "45");
        System.out.println(multiply);
    }
}
