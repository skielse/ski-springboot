package com.ski.bootstart.algorithm;

/**
 * @author wangzijie
 * @date 2023/7/18
 */
public class BoyerMooreVote {
    public static int vote(int[] nums) {
        int candidate = 0, count = 0;
        for (int i : nums) {
            if (count == 0) {
                candidate = i;
            }
            if (candidate == i) {
                count += 1;
            }else {
                count -= 1;
            }
        }
        return candidate;
    }

    public static void main(String[] args) {
        int[] ints = {2, 2, 2, 3, 4, 2, 6};
        int result = vote(ints);
        System.out.println("result = " + result);
    }
}
