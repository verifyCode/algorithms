package sort.basic;

import com.sort.SortTestHelper;

import java.util.Arrays;

/**
 * @author xjn
 * @since 2020-05-07
 */
public class BubbleSort {

    public static void sort(int[] array) {
        for (int i = 0; i < array.length; i++) {
            for (int j = 1; j < array.length - i; j++) {
                if (array[j - 1] > array[j]) {
                    SortTestHelper.swap(array, j - 1, j);
                }
            }
        }
    }

    public static void main(String[] args) {
        int[] ints = SortTestHelper.generateRandomArray(10, 1, 10);
        System.out.println(Arrays.toString(ints));
        sort(ints);
        System.out.println(Arrays.toString(ints));
    }
}
