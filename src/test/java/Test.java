import com.sort.SortTestHelper;

import java.util.Arrays;


/**
 * @author xjn
 * @since 2019-12-07
 */
public class Test {

    public void selectionSort(int[] array) {
        for (int i = 0; i < array.length; i++) {
            int minIndex = i;
            for (int j = i + 1; j < array.length; j++) {
                if (array[minIndex] > array[j]) {
                    minIndex = j;
                }
            }
            SortTestHelper.swap(array, i, minIndex);
        }

    }

    public void insertSort(int[] array) {
        for (int i = 1; i < array.length; i++) {
            int cur = array[i];
            int j;
            for (j = i; j > 0; j--) {
                if (array[j - 1] > cur) {
                    array[j] = array[j - 1];
                } else {
                    break;
                }
            }
            array[j] = cur;
        }
    }

    public void mergeSort(int[] array) {
        mergeSort(array, 0, array.length - 1);
    }

    public void mergeSort(int[] array, int l, int r) {
        if (l >= r) {
            return;
        }
        int mid = (l + r) / 2;
        mergeSort(array, l, mid);
        mergeSort(array, mid + 1, r);
        merge(array, l, mid, r);
    }

    public void merge(int[] array, int l, int mid, int r) {
        int[] aux = new int[r - l + 1];
        for (int i = l; i <= r; i++) {
            aux[i - l] = array[i];
        }
        int i = l;
        int j = mid + 1;
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

    public void quickSort(int[] array) {
        quickSort(array, 0, array.length - 1);
    }

    public void quickSort(int[] array, int left, int right) {
        if (left >= right) {
            return;
        }
        int p = partition(array, left, right);
        quickSort(array, left, p);
        quickSort(array, p + 1, right);
    }

    public int partition(int[] array, int left, int right) {
        int v = array[left];
        int j = left;
        for (int i = left + 1; i <= right; i++) {
            if (array[i] < v) {
                SortTestHelper.swap(array, i, j + 1);
                j++;
            }
        }
        SortTestHelper.swap(array, j, left);
        return j;
    }


    public static void main(String[] args) {
        Test test = new Test();
        int[] ints = SortTestHelper.generateRandomArray(10, 1, 10);
//        selectionSort(ints);
//        insertSort(ints);
//        test.mergeSort(ints);
        test.quickSort(ints);
        System.out.println(Arrays.toString(ints));
    }
}
