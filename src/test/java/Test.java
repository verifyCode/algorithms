import com.sort.SortTestHelper;

import java.io.FileInputStream;
import java.util.Arrays;
import java.util.Properties;
import java.util.Set;


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

    public static int partition(int[] array, int l, int r) {
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

    public static void main1(String[] args) {
        Test test = new Test();
        int[] ints = SortTestHelper.generateRandomArray(10, 1, 10);
//        test.selectSort(ints);
//        test.insertSort(ints);
        test.quickSort(ints);
        System.out.println(Arrays.toString(ints));
    }

    public static void main(String[] args)throws Exception{
        Properties onlineProperties = new Properties();
        FileInputStream online = new FileInputStream("/Users/xujinniu/onlie.properties");
        onlineProperties.load(online);
        Set<String> onlineSet = onlineProperties.stringPropertyNames();

        Properties prePubProperties = new Properties();
        FileInputStream prepub = new FileInputStream("/Users/xujinniu/prepub.properties");
        prePubProperties.load(prepub);
        Set<String> prepubSet = prePubProperties.stringPropertyNames();

        onlineSet.stream().forEach(e -> {
            if (!prepubSet.contains(e)){
                System.out.println(e);
            }
        });
    }
}
