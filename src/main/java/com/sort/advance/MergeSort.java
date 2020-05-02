package com.sort.advance;

import com.sort.SortTestHelper;

import java.util.Arrays;

/**
 * 归并排序
 *
 * @author xjn
 * @since 2019-12-07
 */
public class MergeSort {


    //归并排序 时间复杂度 O(n*log(n))
    //使用O(n)额外的存储空间
    public static void mergeSort(int[] array) {
        sort(array, 0, array.length - 1);
    }

    //递归使用归并排序,对arr[l...r]的范围进行排序
    private static void sort(int[] array, int l, int r) {
        if (l >= r) {
            return;
        }
        int mid = (l + r) / 2;
        sort(array, l, mid);
        sort(array, mid + 1, r);
        merge(array, l, mid, r);
    }


    //将array[l...mid] 和 array[mid + 1 ...r]进行合并
    private static void merge(int[] array, int l, int mid, int r) {
        int[] aux = new int[r - l + 1];
        //将array数组中的数据复制到aux中
        for (int i = l; i <= r; i++) {
            aux[i - l] = array[i];
        }
        //初始化,i指向左半边部分初始索引位置l;j指向右半边部分起始索引位置mid +1
        int i = l, j = mid + 1;
        for (int k = l; k <= r; k++) {
            //左半部分已经处理完毕
            if (i > mid) {
                array[k] = aux[j - l];
                j++;
            } else if (j > r) {//右半边部分已经处理完毕
                array[k] = aux[i - l];
                i++;
            } else if (aux[i - l] < aux[j - l]) {//左半边部分所指元素 < 右半部分所指元素
                array[k] = aux[i - l];
                i++;
            } else {//左半边部分所指元素 >= 右半部分所指元素
                array[k] = aux[j - l];
                j++;
            }
        }
    }

    /*** 自顶向上的归并排序 ***/
    public static void mergeSortBU(int[] array) {

        //1 2 4 8
        int n = array.length;
        //外层循环 对进行merge的元素个数进行遍历 每次sz * 2
        //第一轮 [0,sz-1],[sz,2sz-1]归并
        //第二轮 [2sz,3sz-1],[3sz,4sz-1]归并
        for (int sz = 1; sz <= n; sz += sz) {
            //每次i的位置平移是2个sz
            for (int i = 0; i < n; i += sz + sz) {
                //右半边数组不足,防止越界
                int min = Math.min(i + sz + sz - 1, n - 1);
                //对 arr[i...i+sz-1] 和 arr[i+sz...i+2*sz-1] 进行归并
                merge(array, i, i + sz - 1, min);
            }
        }

    }

    public static void main(String[] args) {
        int[] ints = SortTestHelper.generateRandomArray(10, 1, 10);
//        int[] ints = new int[]{0,1,2};
        mergeSort(ints);
        System.out.println(Arrays.toString(ints));
    }

}
