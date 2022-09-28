package com.yzd.java.fucking_algorithm.array;

import java.util.HashMap;

/**
 * Created by yzd on 2021/12/14
 *
 * 暂定修改
 */
public class SlidingWindow {
    String minWindow(String s, String t) {
        HashMap<Character, Integer> window = new HashMap<>();
        HashMap<Character, Integer> need = new HashMap<>();
        for (char c : t.toCharArray()) {
            need.put(c,1);
        }
            int left = 0, right = 0;
            int vaild = 0;
            char[] sc = s.toCharArray();
            //记录最小覆盖子串的起始索引及长度
            int start = 0, len = Integer.MAX_VALUE;
            while (right < sc.length) {
                // c 是将移入窗口的字符
                char c = sc[right];
                // 右移窗口
                right++;
                // 进行窗口内数据的一系列更新
                if (need.containsKey(c)) {
                    window.put(c, 1);
                    if (window.get(c).equals(need.get(c))) {
                        vaild++;
                    }
                }
            //  判断左侧窗户是否要收缩
                while (vaild == need.size()) {
                    // 在这里更新最小覆盖子串
                    if (right - left < len) {
                        start = left;
                        len = right - left;
                    }
                    // d 是将移出窗口的字符串
                    char d = sc[left];
                    // 左移窗口
                    left++;
                    // 进行窗口内数据的一系列更新
                    if (need.containsKey(d)) {
                        if (window.get(d).equals(need.get(d))) {
                            vaild--;
                            window.remove(d);
                        }
                    }
                }
            }

        return len == Integer.MAX_VALUE ? "" : s.substring(start, len);
    }
}
