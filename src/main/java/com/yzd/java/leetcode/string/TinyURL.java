package com.yzd.java.leetcode.string;

/**
 * Created by yzd on 2021/5/17
 */

import java.util.HashMap;
import java.util.Map;

/**
 * TinyURL是一种URL简化服务， 比如：当你输入一个URL https://leetcode.com/problems/design-tinyurl 时，它将返回一个简化的URL http://tinyurl.com/4e9iAk.
 *
 * 要求：设计一个 TinyURL 的加密 encode 和解密 decode 的方法。你的加密和解密算法如何设计和运作是没有限制的，你只需要保证一个URL可以被加密成一个TinyURL，并且这个TinyURL可以用解密方法恢复成原本的URL。
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/encode-and-decode-tinyurl
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */

/*  思路就是活用HashMap这个数据结构，对于不同的url分别进行加密计算放入map
 *  解密则是根据key来取出原URL
 */
public class TinyURL {
    Map<Integer, String> map = new HashMap<>();

    public String encode(String longUrl) {
        map.put(longUrl.hashCode(), longUrl);
        return "http://tinyurl.com/" + longUrl.hashCode();
    }

    public String decode(String shortUrl) {
        return map.get(Integer.parseInt(shortUrl.replace("http://tinyurl.com/", "")));
    }

    public static void main(String[] args) {
        TinyURL tinyURL = new TinyURL();
        String encode = tinyURL.encode("https://leetcode.com/problems/design-tinyurl");
        System.out.println(encode);
        System.out.println(tinyURL.decode(encode));
    }
}
