package com.xiaodong.java.sort;

import sun.jvm.hotspot.utilities.Assert;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by xiaodong on 2017/5/26.
 * 两个数组中重复的数字
 */
public class TwoArrayEqual {

    public static void main(String[] args) {
        int[] one = new int[]{4, 3, 5, 2, 1};
        int[] two = new int[]{10, 2, 4, 3};
        Set<Integer> set;
        if (one.length <= two.length) {
            set = result(two, one);
        } else {
            set = result(one, two);
        }
        System.out.println(set.toString());
    }

    private static Set<Integer> result(int[] sortData, int[] findData) {
        sort(sortData);
        Set<Integer> set = new HashSet<>();
        for (int a:findData) {
            if (binaryFind(sortData, a)) {
                set.add(a);
            }
        }
        return set;
    }

    private static boolean binaryFind(int[] data, int findData) {
        return binaryFind(data, findData, 0, data.length-1);
    }

    private static boolean binaryFind(int[] data, int findData, int start, int end) {
        if (start > end || data[start]> findData || data[end]< findData) {
            return false;
        }
        int mid = (start + end) / 2;
        int midData = data[mid];
        if (findData == midData) {
            return true;
        } else if (findData < midData) {
            return binaryFind(data, findData, start, mid);
        } else {
            return binaryFind(data, findData, mid + 1, end);
        }
    }

    private static void sort(int[] data) {
        sort(data, 0, data.length-1);
    }

    private static void sort(int[] data, int start, int end) {
        int mid = (start + end)/2;
        if (start < end) {
            sort(data, start, mid);
            sort(data, mid + 1, end);
            merge(data, start, end, mid);
        }

    }

    private static void merge(int[] data, int start, int end, int mid) {
        int[] temp = new int[end - start + 1];
        int k = 0;
        int i = start;
        int j = mid + 1;
        while (i <= mid && j <= end) {
            if (data[i] < data[j]) {
                temp[k++] = data[i++];
            } else {
                temp[k++] = data[j++];
            }
        }
        while (i <= mid) {
            temp[k++] = data[i++];
        }
        while (j <= end) {
            temp[k++] = data[j++];
        }
        for (int t=0; t<temp.length; t++) {
            data[start + t] = temp[t];
        }
    }
}
