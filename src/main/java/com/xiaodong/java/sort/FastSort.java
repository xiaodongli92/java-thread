package com.xiaodong.java.sort;

import java.util.Arrays;

/**
 * Created by xiaodong on 2017/5/25.
 * 快排
 * 时间复杂度（最优O(nlogn) 最差O(n^2)）
 * 非稳定排序
 * 空间复杂度 就地是O(1)
 */
public class FastSort {

    public static void main(String[] args) {
        int[] tests = new int[]{10, 2, 4, 1, 0, 9, 3, 2, 12};
        sort(tests, 0, tests.length-1);
        System.out.println(Arrays.toString(tests));
    }

    public static void sort(int data[], int start, int end) {
        if (start < end) {
            int position = partition(data, start, end);
            sort(data, start, position);
            sort(data, position+1, end);
        }
    }

    private static int partition(int data[], int start, int end) {
        int base = data[start];
        while (start < end) {
            while (start < end && data[end] >= base) {
                end --;
            }
            swap(data, start, end);
            while (start < end && data[start] <= base) {
                start ++;
            }
            swap(data, start, end);
        }
        return start;
    }

    private static void swap(int data[], int a, int b) {
        if (a != b) {
            int temp = data[a];
            data[a] = data[b];
            data[b] = temp;
        }
    }
}
