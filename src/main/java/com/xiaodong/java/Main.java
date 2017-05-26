package com.xiaodong.java;

import java.util.Arrays;
import java.util.Scanner;
import java.util.Set;

/**
 * Created by xiaodong on 2017/5/25.
 */
public class Main {

    public static void main(String[] args) {
        int[] data = new int[]{3,1,6,80,65,0};
//        fast(data);
        merge(data);
        System.out.println(Arrays.toString(data));
    }

    private static void merge(int[] data) {
        merge(data, 0, data.length-1);
    }

    private static void merge(int[] data, int start, int end) {
        int mid = (start + end) / 2;
        if (start < end) {
            merge(data, start, mid);
            merge(data, mid + 1, end);
            mergeSwap(data, start, mid, end);
        }
    }

    private static void mergeSwap(int[] data, int start, int mid, int end) {
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

    private static void fast(int[] data) {
        fast(data, 0, data.length-1);
    }

    private static void fast(int[] data, int start, int end) {
        if (start < end) {
            int position = fastPartition(data, start, end);
            fast(data, 0, position -1);
            fast(data, position+1, end);
        }
    }

    private static int fastPartition(int[] data, int start, int end) {
        int base = data[start];
        while (start < end) {
            while (start<end && data[end]>base) {
                end --;
            }
            swap(data, start, end);
            while (start<end && data[start]<base) {
                start ++;
            }
            swap(data, start, end);
        }
        return start;
    }

    private static void swap(int[] data, int a, int b) {
        int temp = data[a];
        data[a] = data[b];
        data[b] = temp;
    }
}
