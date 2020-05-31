package com.labaldong;

import java.util.stream.Stream;

/**
 * @author xjn
 * @since 2020-05-31
 */
public class SlidingWindow {
    public void slidingWindow(String s, String t) {
        int[] need = new int[128];
        int[] window = new int[128];
        for (int i = 0; i < s.length(); i++) {
            need[s.charAt(i)]++;
        }
        int left = 0;
        int right = 0;
        int valid = 0;
        while (right < s.length()) {
            // c 是将移入窗口的字符
            char c = s.charAt(right);
            // 右移窗口
            right++;
            //...


            //判断左侧窗口是否要收缩
//            while (window needs shrink){
            while (true) {
                char d = s.charAt(left);
                left ++;
//                ...
            }
        }

    }
}
