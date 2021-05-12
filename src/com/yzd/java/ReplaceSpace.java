package com.yzd.java;

/**
 * Created by yzd on 2020/5/20
 * <p>
 * 将一个字符串中的空格替换成 "%20"。
 * <p>
 * Input:
 * "A B"
 * <p>
 * Output:
 * "A%20B"
 */
public class ReplaceSpace {
    public String replaceSpace(StringBuffer str) {
        int P1 = str.length() - 1;
        for (int i = 0; i <= P1; i++) {
            if (str.charAt(i) == ' '){
                str.append("  ");
            }
        }
        int P2 = str.length() - 1;
        while (P1 >= 0 && P2 > P1){
            char c = str.charAt(P1--);
            if (c == ' '){
                str.setCharAt(P2--,'0');
                str.setCharAt(P2--,'2');
                str.setCharAt(P2--,'%');
            }else {
                str.setCharAt(P2--,c);
            }
        }
        return str.toString();
    }

    /**
     * 这两个替换空格思路差不多 但是细节不一样
     * 前一个最后生成字符串大于等于原字符串 后一个则与原字符串长度相同
     */

/**
 * https://leetcode-cn.com/problems/string-to-url-lcci/
 * URL化。编写一种方法，将字符串中的空格全部替换为%20。假定该字符串尾部有足够的空间存放新增字符，
 * 并且知道字符串的“真实”长度。（注：用Java实现的话，请使用字符数组实现，以便直接在数组上操作。）
 */

    public String replaceSpaces(String S, int length) {
        //先把字符串转化为字符数组
        char[] chars = S.toCharArray();
        int index = chars.length - 1;
        for (int i = length - 1; i >= 0; i--) {
            //如果遇到空格就把他转化为"%20"
            if (chars[i] == ' ') {
                chars[index--] = '0';
                chars[index--] = '2';
                chars[index--] = '%';
            } else {
                chars[index--] = chars[i];
            }
        }
        //如果字符留有空间是正好能放下的则index + 1 = 0 递减运算多减了1 实际的索引要加1
        //但是假设留有空间是大于转化后的字符穿的长度 那么此处起始位置就不为0  最后的字符长度为 chars.length-(index + 1)
        return new String(chars, index + 1, chars.length - index - 1);
    }


    public static void main(String[] args) {
        ReplaceSpace replaceSpace = new ReplaceSpace();
//        String s = "Mr John Smith    ";
        String s = "ds sdfs afs sdfa dfssf asdf             ";
        String s1 = replaceSpace.replaceSpaces(s,27);
        System.out.println(s1);
    }
}
