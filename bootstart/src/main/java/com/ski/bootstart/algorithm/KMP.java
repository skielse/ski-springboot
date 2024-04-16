package com.ski.bootstart.algorithm;

import lombok.extern.slf4j.Slf4j;

/**
 * @author ski
 * @date 2022/6/15
 */
@Slf4j
public class KMP {

    /**
     * @param ts   主串
     * @param patt 模式串
     * @return 匹配是返回主串位置下标，否则-1
     */
    public static int check(String ts, String patt) {
        char[] t = ts.toCharArray();
        char[] p = patt.toCharArray();
        //主串位置
        int i = 0;
        //模式串位置
        int j = 0;
        int[] next = new int[p.length];
        kmpNext(p, next);
        log.info("next=={}", next);
        while (i < t.length && j < p.length) {
            //当j=-1时，移动的是i，当然j也要归零
            if (j == -1 || t[i] == p[j]) {
                i++;
                j++;
            } else {
                /*
                i不需要回溯了
                i = i - j + 1
                 */
                j = next[j];
            }
        }
        if (j == p.length) {
            return i - j;
        } else {

            return -1;
        }
    }

    static void kmpNext(char[] patt, int[] next) {
        int i = 0;
        int j = -1;
        int n = patt.length - 1;

        next[0] = -1;
        while (i < n) {
            if (j == -1 || patt[i] == patt[j]) {
                next[++i] = ++j;
            } else {
                j = next[j];
            }

        }
    }

    public static void main(String[] args) {
        log.info("==========test begin==========");
        String ts = "asdfasdfwafasdf";
        String patt = "dfwa";
        int index = KMP.check(ts, patt);
        log.info("index===={}", index);

    }
}
