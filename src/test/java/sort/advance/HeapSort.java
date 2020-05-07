package sort.advance;

import com.sort.SortTestHelper;

import java.util.Arrays;

/**
 * @author xjn
 * @since 2020-05-07
 */
public class HeapSort {

    public static void heapSort(int[] array) {
        for (int i = (array.length - 1 - 1) / 2; i >= 0; i--) {
            shiftDown(array, array.length, i);
        }

        for (int i = array.length - 1; i > 0; i--) {
            SortTestHelper.swap(array, i, 0);
            shiftDown(array, i, 0);
        }
    }

    public static void shiftDown(int[] array, int n, int index) {
        while (index * 2 + 1 < n) {
            int j = 2 * index + 1;
            if (j + 1 < n && array[j + 1] > array[j]) {
                j = j + 1;
            }
            if (array[index] > array[j]) {
                break;
            }
            SortTestHelper.swap(array, j, index);
            index = j;
        }
    }


    public static void main(String[] args) {
        int[] ints = SortTestHelper.generateRandomArray(10, 1, 10);
        System.out.println(Arrays.toString(ints));
        heapSort(ints);
        System.out.println(Arrays.toString(ints));
    }
}
