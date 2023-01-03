package com.ski.bootstart.algorithm;

import lombok.extern.slf4j.Slf4j;

import java.util.Arrays;

/**
 * @author wangzijie
 * @date 2023/1/3
 */
@Slf4j
public class KnuthDurstenfeldShuffle {


    /**
     * 算法步骤为：
     * 1. 建立一个数组大小为 n 的数组 arr，分别存放 1 到 n 的数值；
     * 2. 生成一个从 0 到 n - 1 的随机数 x；
     * 3. 输出 arr 下标为 x 的数值，即为第一个随机数；
     * 4. 将 arr 的尾元素和下标为 x 的元素互换；
     * 5. 同2，生成一个从 0 到 n - 2 的随机数 x；
     * 6. 输出 arr 下标为 x 的数值，为第二个随机数；
     * 7. 将 arr 的倒数第二个元素和下标为 x 的元素互换；
     * ……
     * 如上，直到输出 m 个数为止
     * <p>
     * 由于是从后往前扫描，无法处理不知道长度或动态增长的数组。
     * @return 洗牌后的数组
     */
    public static <T> T[] shuffle(T[] arr) {
        int len = arr.length;
        for (int i = 0; i < len; i++) {
            int index = (int) (Math.random() * (len - i));
            log.info("index=={}", index);
            T tmp = arr[index];
            arr[index] = arr[len - i - 1];
            arr[len - i - 1] = tmp;
        }
        return arr;
    }


    public static void main(String[] args) {
        Object[] arr = {"A", "B", 1, "D", "E"};
        Object[] newArr = shuffle(arr);
        log.info(Arrays.toString(newArr));
    }
}
