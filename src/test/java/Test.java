import com.sort.SortTestHelper;

import java.io.FileInputStream;
import java.util.*;


/**
 * @author xjn
 * @since 2019-12-07
 */
public class Test {


    public static void mergeSort(int[] array) {
        sort(array, 0, array.length - 1);
    }

    private static void sort(int[] array, int l, int r) {
        if (l >= r) {
            return;
        }
        int mid = (l + r) / 2;
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
        for (int k = l; k <= r; k++) {
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


    public static void quickSort(int[] array) {
        quickSort(array, 0, array.length - 1);
    }

    private static void quickSort(int[] array, int l, int r) {
        if (l >= r) {
            return;
        }
        int p = partition(array, l, r);
        quickSort(array, l, p);
        quickSort(array, p + 1, r);
    }

    private static int partition(int[] array, int l, int r) {
        int v = array[l];
        int j = l + 1;
        for (int i = j; i <= r; i++) {
            if (array[i] < v) {
                SortTestHelper.swap(array, i, j + 1);
                j++;
            }
        }
        SortTestHelper.swap(array, j, l);
        return j;
    }

    public static void main(String[] args) {
        int[] ints = SortTestHelper.generateRandomArray(10, 1, 10);
        mergeSort(ints);
        System.out.println(Arrays.toString(ints));
    }
}

