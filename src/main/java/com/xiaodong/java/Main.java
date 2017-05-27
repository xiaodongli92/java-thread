package com.xiaodong.java;

import java.util.Arrays;
import java.util.Scanner;
import java.util.Set;

/**
 * Created by xiaodong on 2017/5/25.
 */
public class Main {

    public static void main(String[] args) {
        int[] data = new int[]{3, 1, 10, 0, 2, 4};
//        fast(data);
        merge(data);
        System.out.println(Arrays.toString(data));
    }

    public static void merge(int[] data) {
        merge(data, 0, data.length-1);
    }

    private static void merge(int[] data, int start, int end) {
        int mid = (start + end)/2;
        if (start < end) {
            merge(data, start, mid-1);
            merge(data, mid+1, end);
            merge(data, start, end, mid);
        }
    }

    private static void merge(int[] data, int start, int end, int mid) {
        int[] temp = new int[end - start + 1];
        int i = start;
        int j = mid+1;
        int k = 0;
        while (i <= mid && j<= end) {
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
        for (int z = 0; z < temp.length; z++) {
            data[start + z] = temp[z];
        }
    }

    public static void fast(int[] data) {
        fast(data, 0, data.length - 1);
    }

    private static void fast(int[] data, int start, int end) {
        if (start < end) {
            int mid = change(data, start, end);
            fast(data, start, mid);
            fast(data, mid + 1, end);
        }
    }

    private static int change(int[] data, int start, int end) {
        int base = data[start];
        while (start < end) {
            while (start < end && base < data[end]) {
                end --;
            }
            swap(data, start, end);
            while (start < end && base > data[start]) {
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
