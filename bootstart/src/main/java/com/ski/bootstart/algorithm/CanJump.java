package com.ski.bootstart.algorithm;

/**
 * @author wangzijie
 * @date 2024/2/20
 */
public class CanJump {

    public boolean canJump(int[] nums) {
        int n = nums.length;
        int rightmost = 0;
        for (int i = 0; i < n; ++i) {
            if (i <= rightmost) {
                rightmost = Math.max(rightmost, i + nums[i]);
                if (rightmost >= n - 1) {
                    return true;
                }
            }
        }
        return false;
    }

    public static void main(String[] args) {
        int[] nums = new int[]{3, 2, 1, 0, 4, 6};
        CanJump canJump = new CanJump();
        System.out.println(canJump.canJump(nums));
    }
}
