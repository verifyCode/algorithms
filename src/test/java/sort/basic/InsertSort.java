package sort.basic;

import com.sort.SortTestHelper;

import java.util.Arrays;

/**
 * @author xjn
 * @since 2020-05-07
 */
public class InsertSort {

    public static void sort(int[] array) {
        for (int i = 1; i < array.length; i++) {
            for (int j = i; j > 0; j--) {
                if (array[j - 1] > array[j]) {
                    SortTestHelper.swap(array, j, j - 1);
                } else {
                    break;
                }
            }
        }
    }

    public static void sort2(int[] array) {
        for (int i = 1; i < array.length; i++) {
            int element = array[i];
            int j;
            for (j = i; j > 0; j--) {
                if (array[j - 1] > element) {
                    array[j] = array[j - 1];
                } else {
                    break;
                }
            }
            array[j] = element;
        }
    }


    public static void main(String[] args) {
        int[] ints = SortTestHelper.generateRandomArray(10, 1, 10);
        System.out.println(Arrays.toString(ints));
        sort2(ints);
        System.out.println(Arrays.toString(ints));
    }
}
