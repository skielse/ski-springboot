package com.ski.bootstart.algorithm;

/**
 * @author ski
 * @date 2024/2/6
 */
public class MaxSubArray {

    public int maxSubArray(int[] nums) {
        int pre = 0, maxAns = nums[0];
        for (int x : nums) {
            pre = Math.max(pre + x, x);
            maxAns = Math.max(maxAns, pre);
        }
        return maxAns;
    }

}
