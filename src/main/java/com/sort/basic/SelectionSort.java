package com.sort.basic;

import com.sort.SortTestHelper;

/**
 * 选择排序:
 * 复杂度:O(n^2)
 * 空间复杂度:O(1)
 *
 * @author xjn
 * @since 2019-12-05
 */
public class SelectionSort {

    /**
     * 选择排序
     * 时间复杂度:O(n^2)
     * 稳定(按照实现也可以不稳定)
     */
    public static void selectionSort(int[] array) {
        for (int i = 0; i < array.length; i++) {
            //假设当前index是最小值
            int minIndex = i;
            //在[i+1 ... n]中找到最小值
            for (int j = i + 1; j < array.length; j++) {
                //循环完毕后minIndex是最小值所在索引
                if (array[minIndex] > array[j]) {
                    minIndex = j;
                }
            }
            //交换
            SortTestHelper.swap(array, minIndex, i);
        }
    }

    public static void main(String[] args) {
        long start = System.currentTimeMillis();
        int[] ints = SortTestHelper.generateRandomArray(10000, 1, 10);
        selectionSort(ints);
        long end = System.currentTimeMillis();
        System.out.println((end - start) / 1000.0);
    }
}
