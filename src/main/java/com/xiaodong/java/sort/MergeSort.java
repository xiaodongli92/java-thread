package com.xiaodong.java.sort;

import java.util.Arrays;

/**
 * Created by xiaodong on 2017/5/25.
 * 归并排序
 * 时间复杂度O(nlogn)
 * 稳定排序
 * 空间复杂度O（n）
 */
public class MergeSort {

    public static void main(String[] args) {
        int[] tests = new int[]{10, 2, 4, 1, 0, 9, 3, 2, 12};
        System.out.println("将要排序：" + Arrays.toString(tests));
        sort(tests, 0, tests.length - 1);
        System.out.println(Arrays.toString(tests));
    }

    public static void sort(int[] values, int start, int end) {
        int mid = (start + end) / 2;
        if (start < end) {
            sort(values, start, mid);
            sort(values, mid+1, end);
            merge(values, start, mid, end);
        }
    }

    private static void merge(int[] values, int start, int mid, int end) {
        int[] temp = new int[end - start + 1];
        int t = 0;
        int i = start;
        int j = mid + 1;
        while (i <= mid && j<= end) {
            if (values[i] < values[j]) {
                temp[t++] = values[i++];
            } else {
                temp[t++] = values[j++];
            }
        }
        while (i <= mid) {
            temp[t++] = values[i++];
        }
        while (j <= end) {
            temp[t++] = values[j++];
        }
        for (int p=0; p<temp.length; p++) {
            values[start + p] = temp[p];
        }
        System.out.println(Arrays.toString(values));
    }
}
