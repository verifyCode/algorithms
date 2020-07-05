package com.sort.advance;

import com.sort.SortTestHelper;

import java.util.Arrays;

/**
 * @author xjn
 * @since 2020-05-01
 */
public class ShellSort {

    public static void shellSort(int[] array) {
        int h = 1;
        while (h < array.length / 3) {
            h = 3 * h + 1;
        }

        while (h >= 1) {
            for (int i = h; i < array.length; i++) {
                int element = array[i];
                int j;
                for (j = i; j >= h; j -= h) {
                    if (element < array[j - h]) {
                        array[j] = array[j - h];
                    }else {
                        break;
                    }
                }
                array[j] = element;
            }
            h /= 3;
        }
    }

    public static void main(String[] args) {
        int[] ints = SortTestHelper.generateRandomArray(10, 1, 10);
        System.out.println(Arrays.toString(ints));
        shellSort(ints);
        System.out.println(Arrays.toString(ints));
    }
}
