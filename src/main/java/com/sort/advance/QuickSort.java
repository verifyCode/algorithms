package com.sort.advance;

import com.sort.SortTestHelper;

import java.util.Arrays;

/**
 * @author xjn
 * @since 2019-12-08
 */
public class QuickSort {
    public static void quickSort(int[] array) {
        quickSort(array, 0, array.length - 1);
    }

    //对array[l...r]进行快速排序
    public static void quickSort(int[] array, int l, int r) {
        if (l >= r) {
            return;
        }
        int p = partition(array, l, r);
        quickSort(array, l, p);
        quickSort(array, p + 1, r);
    }

    //array[l...r]进行partition操作
    //返回p 使得 array[l...p-1] < array[p] < array[p+1,r]
    public static int partition(int[] array, int l, int r) {
        int v = array[l];
        //array[l+1...j] < v; array[j+1...r] > v;
        //[a,a,a,v,a,a,a,]
        int j = l;
        for (int i = l + 1; i <= r; i++) {
            if (array[i] < v) {
                SortTestHelper.swap(array, i, j + 1);
                j++;
            }
        }
        SortTestHelper.swap(array, j, l);
        return j;
    }

    //双路快速排序处理array[l...r]
    //将array[l...r]
    public static int partition2Way(int[] array, int l, int r) {
        int v = array[l];
        //array[l+1...i) <= v; array(j...r] >= v
        int i = l + 1, j = r;
        while (true) {
            while (i <= r && array[i] < v) {
                i++;
            }
            while (j >= l + 1 && array[j] > v) {
                j--;
            }
            if (i > j) {
                break;
            }
            SortTestHelper.swap(array, i, j);
            i++;
            j--;
        }
        SortTestHelper.swap(array, i, j);
        return j;
    }


    public static void partition3Way(int[] array, int l, int r) {
        int v = array[l];
        int lt = l; //array[l+1...lt] < v
        int gt = r + 1;//array[gt...r] > v
        int i = l + 1;//array[lt+1...i] == v
        while (i < gt) {
            if (array[i] < v) {
                SortTestHelper.swap(array, i, lt + 1);
                i++;
                lt++;
            } else if (array[i] > v) {
                SortTestHelper.swap(array, i, gt - 1);
                //i 不需要动,因为此时交换操作之后,i指向的是没有被处理的元素
                gt--;
            } else {
                //arr[i] == v;
                i++;
            }
        }
        SortTestHelper.swap(array, l, lt);
        quickSort(array, l, lt - 1);
        quickSort(array, gt, r);
    }

    public static void main(String[] args) {
        int[] ints = SortTestHelper.generateRandomArray(10, 1, 10);
        System.out.println(Arrays.toString(ints));
        quickSort(ints);
        System.out.println(Arrays.toString(ints));
    }
}
