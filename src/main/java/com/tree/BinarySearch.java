package com.tree;

/**
 * @author xjn
 * @since 2019-12-09
 * 二分查找
 */
public class BinarySearch {

    public static int binarySearch(int[] array, int target) {
        int l = 0;
        int r = array.length - 1;
        while (l <= r) {
            int mid = (l + r) / 2;
            if (array[mid] == target) {
                return mid;
            } else if (array[mid] > target) {
                r = mid - 1;
            } else {
                l = mid + 1;
            }
        }
        return -1;
    }

    public static int binarySearchRecursive(int[] array, int target) {
        return binarySearchRecursive(array, 0, array.length, target);
    }

    private static int binarySearchRecursive(int[] array, int l, int r, int target) {
        if (l >= r) {
            return -1;
        }
        int mid = (l + r) / 2;
        if (array[mid] == target) {
            return mid;
        } else if (array[mid] > target) {
            return binarySearchRecursive(array, l, mid - 1, target);
        } else {
            return binarySearchRecursive(array, mid + 1, r, target);
        }
    }

    public static void main(String[] args) {
        int[] ints = new int[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 0};
        System.out.println(binarySearch(ints, 4));
        System.out.println(binarySearchRecursive(ints, 4));
    }
}
