package sort.basic;

import com.sort.SortTestHelper;

import java.util.Arrays;

/**
 * @author xjn
 * @since 2020-05-07
 */
public class ShellSort {
    public static void sort(int[] array) {
        int h = 1;
        while (h < array.length / 3) {
            h = 3 * h + 1;
        }
        while (h >= 1) {
            for (int i = h; i < array.length; i++) {
                int element = array[i];
                int j;
                for (j = i; j >= h; j--) {
                    if (array[j - 1] > element) {
                        array[j] = array[j - 1];
                    } else {
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
        sort(ints);
        System.out.println(Arrays.toString(ints));
    }
}
