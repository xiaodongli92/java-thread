package com.xiaodong.java.sort;

import java.util.Arrays;

/**
 * Created by xiaodong on 2017/5/25.
 * 冒泡排序
 * 时间复杂度 O(n^2)
 * 稳定排序
 * 空间复杂度 0（1）
 */
public class BubbleSort {

    public static void main(String[] args) {
        int[] tests = new int[]{10, 2, 4, 1, 0, 9, 3, 2, 12};
        sort(tests);
        System.out.println(Arrays.toString(tests));
    }

    public static void sort(int[] data) {
        int temp;
        for (int i=0; i<data.length - 1; i++) {
            for (int j=i+1; j<data.length; j++) {
                if (data[i] > data[j]) {
                    temp = data[i];
                    data[i] = data[j];
                    data[j] = temp;
                }
            }
        }
    }
}
