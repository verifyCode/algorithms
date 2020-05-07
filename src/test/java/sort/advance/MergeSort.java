package sort.advance;

import com.sort.SortTestHelper;

import java.util.Arrays;

/**
 * @author xjn
 * @since 2020-05-07
 */
public class MergeSort {

    public static void mergeSort(int[] array) {
        sort(array, 0, array.length - 1);
    }

    private static void sort(int[] array, int l, int r) {
        if (l >= r) {
            return;
        }
        int mid = (r + l) / 2;
        sort(array, l, mid);
        sort(array, mid + 1, r);
        merge(array, l, mid, r);
    }

    private static void merge(int[] array, int l, int mid, int r) {
        int[] aux = new int[r - l + 1];
        for (int i = l; i <= r; i++) {
            aux[i - l] = array[i];
        }
        int i = l, j = mid + 1;
        for (int k = i; k <= r; k++) {
            if (i > mid) {
                array[k] = aux[j - l];
                j++;

            } else if (j > r) {
                array[k] = aux[i - l];
                i++;
            } else if (aux[i - l] > aux[j - l]) {
                array[k] = aux[j - l];
                j++;
            } else {
                array[k] = aux[i - l];
                i++;
            }
        }
    }

    public static void main(String[] args) {
        int[] ints = SortTestHelper.generateRandomArray(10, 1, 10);
        System.out.println(Arrays.toString(ints));
        mergeSort(ints);
        System.out.println(Arrays.toString(ints));
    }
}
