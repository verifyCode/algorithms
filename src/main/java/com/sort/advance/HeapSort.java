package com.sort.advance;

import com.sort.SortTestHelper;

import java.util.Arrays;

/**
 * @author xjn
 * @since 2019-12-09
 */
public class HeapSort {

    public static void heapSort(int[] array) {
        //heapify
        //[https://coding.imooc.com/lesson/71.html#mid=34008]
        //最后一个非叶子节点的计算公式不是 (count - 1) / 2 而是(最后一个元素索引值 - 1)/2
        for (int i = (array.length - 1 - 1) / 2; i >= 0; i--) {
            shiftDown(array, array.length, i);
        }
        //在>=1的位置进行最后一次的交换操作,当最后堆中只剩一个元素的时候,不需要进行任何操作
        for (int i = array.length - 1; i > 0; i--) {
            SortTestHelper.swap(array, i, 0);
            shiftDown(array, i, 0);
        }
    }

    //对于大小为n的堆array,在index位置进行shiftDown
    public static void shiftDown(int[] array, int n, int index) {
        //当其存在左节点
        //注意这里是 <n 而不是 <= n,因为n在这里是数组长度
        while (2 * index + 1 < n) {
            //暂存左节点
            int j = 2 * index + 1;
            //存在右节点 && 右节点的值大于左节点的值
            if (j + 1 < n && array[j] < array[j + 1]) {
                j = j + 1;
            }
            //本身已经比子节点要大没有交换的必要
            if (array[index] >= array[j]) {
                break;
            }
            SortTestHelper.swap(array, j, index);
            index = j;
        }

    }

    public static void main(String[] args) {
        int[] ints = SortTestHelper.generateRandomArray(10, 1, 10);
        heapSort(ints);
        System.out.println(Arrays.toString(ints));
    }
}
