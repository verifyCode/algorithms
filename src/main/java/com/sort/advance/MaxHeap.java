package com.sort.advance;

import com.sort.SortTestHelper;

/**
 * @author xjn
 * @since 2019-12-08
 */
public class MaxHeap {
    private int[] data;
    private int count;

    public MaxHeap(int capacity) {
        data = new int[capacity + 1];
    }

    public MaxHeap(int[] array, int capacity) {
        data = new int[capacity + 1];
        for (int i = 0; i < array.length; i++) {
            data[i + 1] = array[i];
        }
        count = capacity;

        for (int i = count / 2; i >= 1; i--) {
            shiftDown(i);
        }
    }

    public MaxHeap() {
        this(10);
    }

    public int getSize() {
        return count;
    }

    public boolean isEmpty() {
        return count == 0;
    }

    //插入一条元素
    public void insert(int e) {
        data[count + 1] = e;
        count++;
        shiftUp(count);
    }

    /**
     * 保证父节点最大,维持堆定义的正确性,向堆中插入元素
     *
     * @param k
     */
    private void shiftUp(int k) {
        while (k > 1 && data[k / 2] < data[k]) {
            SortTestHelper.swap(data, k / 2, k);
            k /= 2;
        }
    }

    public int extractMax() {
        int res = data[1];
        SortTestHelper.swap(data, count, 1);
        count--;
        shiftDown(1);
        return res;
    }

    /**
     * 从堆中取出元素 思路(取出最根节点,末尾节点填补,)
     */
    private void shiftDown(int k) {
        //当其有叶子节点的时候
        //在完全二叉树中,当其有左叶子节点
        while (2 * k <= count) {
            int j = 2 * k;//在此轮循环中,data[k]和data[j]交换位置
            //存在右子节点 && 右子节点大于左子节点
            if (j + 1 < count && data[j + 1] > data[j]) {
                //更新j的值
                j += 1;
            }
            //当前值大于其最大子节点
            if (data[k] >= data[j]) {
                break;
            }
            //交换值
            SortTestHelper.swap(data, j, k);
            k = j;
        }

    }

    public static void main(String[] args) {

    }
}
