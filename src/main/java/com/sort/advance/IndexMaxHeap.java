package com.sort.advance;

import com.sort.SortTestHelper;

/**
 * @author xjn
 * @since 2019-12-09
 */
public class IndexMaxHeap {
    private int[] data;
    /*** 存取索引 ***/
    private int[] index;
    private int count;
    private int capacity;

    public IndexMaxHeap(int capacity) {
        this.data = new int[capacity + 1];
        this.index = new int[capacity + 1];
        this.count = 0;
        this.capacity = capacity;
    }

    public IndexMaxHeap() {
        this(10);
    }

    public int getSize() {
        return count;
    }

    public boolean isEmpty() {
        return count == 0;
    }


    //传入的i对用户而言是从0索引的
    public void insert(int i, int e) {
//        data[count + 1] = e;
//        count++;
//        shiftUp(count);
        i += 1;
        data[i] = e;
        index[count + 1] = i;
        count++;
        shiftUp(count);
    }

    public int extractMax() {
        int res = data[index[1]];
        SortTestHelper.swap(index, count, 1);
        count--;
        shiftDown(1);
        return res;
    }

    private void shiftDown(int k) {
        //当其有叶子节点的时候
        //在完全二叉树中,当其有左叶子节点
        while (2 * k <= count) {
            int j = 2 * k;//在此轮循环中,data[k]和data[j]交换位置
            //存在右子节点 && 右子节点大于左子节点
            if (j + 1 < count && data[index[j + 1]] > data[index[j]]) {
                //更新j的值
                j += 1;
            }
            if (data[index[k]] >= data[index[j]]) {
                break;
            }
            //交换值
            SortTestHelper.swap(index, j, k);
            k = j;
        }

    }

    public int extractIndexMax() {
        int ret = index[1] - 1;
        SortTestHelper.swap(index, count, 1);
        count--;
        shiftDown(1);
        return ret;
    }

    public int getItem(int i) {
        return data[i + 1];
    }

    public void change(int i, int newItem) {
        i += 1;
        data[i] = newItem;
        //正确进行shiftDown和shiftUp,需要知道当前data[i]在堆中的位置
        //即index[j] = i,j标识data[i]在堆中的位置
        for (int j = 1; j <= count; j++) {
            if (index[j] == i) {
                shiftDown(j);
                shiftUp(j);
            }
        }
    }


    private void shiftUp(int k) {
        while (k > 1 && data[index[k / 2]] < data[index[k]]) {
            SortTestHelper.swap(index, k / 2, k);
            k /= 2;
        }
    }


}
