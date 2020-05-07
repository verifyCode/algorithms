package sort.advance;

import com.sort.SortTestHelper;

import java.util.Arrays;

/**
 * @author xjn
 * @since 2020-05-07
 */
public class QuickSort {

    public static void quickSort(int[] array) {
        quickSort(array, 0, array.length - 1);
    }

    private static void quickSort(int[] array, int l, int r) {
        if (l >= r) {
            return;
        }
        int p = partition(array, l, r);
        quickSort(array, l, p - 1);
        quickSort(array, p + 1, r);
    }


    private static int partition(int[] array, int l, int r) {
        int v = array[l];
        int j = l;
        for (int i = l + 1; i <= r; i++) {
            if (array[i] < v) {
                SortTestHelper.swap(array, i, j + 1);
                j++;
            }
        }
        SortTestHelper.swap(array, j, l);
        return j;
    }

    private static int partition2way(int[] array, int l, int r) {
        int v = array[l];
        int i = l + 1, j = r;
        while (true) {
            while (i <= r && array[i] < v) {
                i++;
            }
            while (j >= l + 1 && array[j] > v) {
                j--;
            }
            if (i > j) {
                break;
            }
            SortTestHelper.swap(array, i, j);
            i++;
            j--;
        }
        SortTestHelper.swap(array, l, j);
        return j;
    }


    private static void partition3way(int[] array, int l, int r) {
        int v = array[l];
        int lt = l;
        int i = l + 1;
        int gt = r + 1;
        while (i < gt) {
            if (array[i] < v) {
                SortTestHelper.swap(array, lt + 1, i);
                lt++;
                i++;
            } else if (array[i] > v) {
                SortTestHelper.swap(array, gt - 1, i);
                gt--;
            } else {
                i++;
            }
        }
        SortTestHelper.swap(array, l, lt);
        partition3way(array, l, lt - 1);
        partition3way(array, gt, r);
    }

    public static void main(String[] args) {
        int[] ints = SortTestHelper.generateRandomArray(10, 1, 10);
        System.out.println(Arrays.toString(ints));
        quickSort(ints);
        System.out.println(Arrays.toString(ints));

    }
}
