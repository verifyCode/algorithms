/**
 * @author xjn
 * @since 2020-05-22
 */
public class BinarySearch {

    public int binarySearch(int[] array, int target) {
        if (array == null || array.length == 0) {
            return -1;
        }
        int left = 0;
        int right = array.length - 1;
        while (left <= right) {
            int mid = (left + right) / 2;
            if (target == array[mid]) {
                return mid;
            } else if (target < array[mid]) {
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }
        return -1;
    }

    public static void main(String[] args) {
        int[] array = new int[]{1, 2, 3, 4, 5, 6, 7, 9};
        BinarySearch binarySearch = new BinarySearch();
        System.out.println(binarySearch.binarySearch(array, 2));
    }
}
