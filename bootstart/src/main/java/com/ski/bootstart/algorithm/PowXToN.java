package com.ski.bootstart.algorithm;

/**
 * @author wangzijie
 * @date 2023/12/15
 */
public class PowXToN {
    public double myPow(double x, int n) {
        return (long) n >= 0 ? quickMul(x, n) : 1.0 / quickMul(x, -(long) n);
    }

    public double quickMul(double x, long N) {
        double ans = 1.0;
        // 贡献的初始值为 x
        double xContribute = x;
        // 在对 N 进行二进制拆分的同时计算答案
        while (N > 0) {
            if (N % 2 == 1) {
                // 如果 N 二进制表示的最低位为 1，那么需要计入贡献
                ans *= xContribute;
            }
            // 将贡献不断地平方
            xContribute *= xContribute;
            // 舍弃 N 二进制表示的最低位，这样我们每次只要判断最低位即可
            N /= 2;
        }
        return ans;
    }

    public static void main(String[] args) {
        PowXToN powXToN = new PowXToN();
        double myPow = powXToN.myPow(4, 5);
        System.out.println("myPow=" + myPow);
    }
}
