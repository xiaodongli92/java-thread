package com.xiaodong.java.tree;

import com.xiaodong.java.sort.FastSort;

import java.util.Arrays;

/**
 * Created by xiaodong on 2017/5/25.
 */
public class BinaryFind {

    public static void main(String[] args) {
        int[] tests = new int[]{10, 2, 4, 1, 0, 9, 3, 2, 12};
        FastSort.sort(tests, 0, tests.length-1);
        System.out.println(Arrays.toString(tests));
        System.out.println(find(tests, 2));
    }

    private static int find(int[] data, int findData) {
        return find(data, 0, data.length-1, findData);
    }

    private static int find(int[] data, int start, int end, int findData) {
        if (start > end || findData > data[end] || findData < data[start]) {
            return -1;
        }
        int mid = (start + end) / 2;
        if (data[mid] == findData) {
            return mid;
        } else if (data[mid] < findData) {
            return find(data, mid + 1, end, findData);
        } else {
            return find(data, start, mid - 1, findData);
        }
    }
}
