package com.ski.bootstart.algorithm;

/**
 * @author wangzijie
 * @date 2022/6/15
 */
public class KMP {

    void kmpNext(char[] patt, int[] next)
    {
        int i = 0;
        int j = 1;
        int n = patt.length;

        next[i] = next[j] = 0;
        while (j < n)
        {
            if (i == 0 || patt[i] == patt[j])
            {
                ++i;
                next[j + 1] = i;
                ++j;
            }
            else
            {
                i = next[i];
            }

        }
    }

}
