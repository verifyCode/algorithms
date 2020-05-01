package com.sort.basic;

import com.sort.SortTestHelper;

/**
 * @author xjn
 * @since 2019-12-05
 */
public class InsertSort {

    /**
     * 插入排序
     * 时间复杂度 O(n^2)
     * 稳定排序((按照实现也可以不稳定))
     *
     * @param array
     */
    public static void insertSort(int[] array) {
        //i=1开始:对于插入排序来说 第0个元素不用考虑,本身已经有序,
        //不需要把它再插入到前面的位置
        for (int i = 1; i < array.length; i++) {
            //寻找第array[i]合适的插入位置
            //为什么什么 'j > 0' 而不是 'j >= 0'
            //因为每次都是当前元素和前一个元素作比较,这个比较最后发生的位置应该是j=1的时候,也就是当j=1的时候让他和0的位置进行比较
            for (int j = i; j > 0; j--) {
                // j:当前考察元素的位置
                //当前的值和他面的值比较,如果当前值小,则交换,否则break
                if (array[j] < array[j - 1]) {
                    SortTestHelper.swap(array, j, j - 1);
                } else {
                    //当前值比前面的大,标识已经在合适的位置了,不需要交换
                    break;
                }
            }
        }
    }

    public static void insertSort2(int[] array) {
        for (int i = 1; i < array.length; i++) {
            //暂存当前元素,直到找到合适的交换位置
            int element = array[i];
            //暂存下标-找到合适的位置和当前元素进行交换
            int j;
            for (j = i; j > 0; j--) {
                //前一个元素比当前元素小,直接替换
                if (array[j - 1] > element) {
                    array[j] = array[j - 1];
                } else {
                    //否则break 保留当前j的值
                    break;
                }
            }
            array[j] = element;
        }
    }

    public static void main(String[] args) {
        int[] array = SortTestHelper.generateRandomArray(10, 1, 10);
        for (int i = 1; i < array.length; i++) {

        }

    }
}
