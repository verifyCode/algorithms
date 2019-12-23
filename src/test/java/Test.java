import com.sort.SortTestHelper;

import java.util.Arrays;


/**
 * @author xjn
 * @since 2019-12-07
 */
public class Test {

    public void insertSort(int[] array) {
        for (int i = 1; i < array.length; i++) {
            int v = array[i];
            int j;
            for (j = i; j > 0; j--) {
                if (array[j - 1] > v) {
                    array[j] = array[j - 1];
                } else {
                    break;
                }
            }
            array[j] = v;
        }
    }

    public void selectSort(int[] array) {
        for (int i = 0; i < array.length; i++) {
            int minIndex = i;
            for (int j = i + 1; j < array.length; j++) {
                if (array[minIndex] > array[j]) {
                    minIndex = j;
                }
            }
            SortTestHelper.swap(array, minIndex, i);
        }
    }

    public void mergeSort(int[] array) {

    }

    public void sort(int[] array, int l, int r) {
        if (l >= r) {
            return;
        }
        int mid = (l + r) / 2;
        sort(array, l, mid);
        sort(array,mid +1,r);
    }

    public static void main(String[] args) {
        Test test = new Test();
        int[] ints = SortTestHelper.generateRandomArray(10, 1, 10);
        test.selectSort(ints);
//        test.insertSort(ints);
        System.out.println(Arrays.toString(ints));
    }
}
