package com.sort;

import java.util.Arrays;
import java.util.Objects;
import java.util.Random;

/**
 * @author xjn
 * @since 2019-12-05
 */
public final class SortTestHelper<E> {

    //生成[rangL ... rangR]含有n个元素的数组
    public static int[] generateRandomArray(int n, int rangL, int rangR) {
        int[] array = new int[n];

        Random random = new Random(System.currentTimeMillis());
        for (int i = 0; i < array.length; i++) {
            int res = (Math.abs(random.nextInt()) % (rangR - rangL + 1) + rangL);
            array[i] = res;
        }
        return array;
    }

    public static void swap(int[] array, int aIndex, int bIndex) {
        int a = array[aIndex];
        int b = array[bIndex];
        array[aIndex] = b;
        array[bIndex] = a;
    }

    public static void swap(Object[] array, int aIndex, int bIndex) {
        Object a = array[aIndex];
        Object b = array[bIndex];
        array[aIndex] = b;
        array[bIndex] = a;
    }

}
