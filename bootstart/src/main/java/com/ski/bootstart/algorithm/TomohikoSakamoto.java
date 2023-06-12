package com.ski.bootstart.algorithm;

/**
 * @author wangzijie
 * @date 2023/6/12
 */
public class TomohikoSakamoto {

    public static int dow(int y, int m, int d) {
        int[] t = {0, 3, 2, 5, 0, 3, 5, 1, 4, 6, 2, 4};
        if (m < 3) {
            y -= 1;
        }
        return (y + y / 4 - y / 100 + y / 400 + t[m - 1] + d) % 7;
    }

    public static void main(String[] args) {
        int dow = dow(2023, 6, 10);
        System.out.println(dow);
    }
}
