package com.heap_priority_queue;

import com.array.Array;
import com.sort.SortTestHelper;
import java.util.Arrays;

/**
 * @author xjn
 * @since 2019-12-26
 */
public class MaxHeap<E extends Comparable> implements Heap<E> {
    private Array<E> array;

    public MaxHeap(int capacity) {
        array = new Array<>(capacity);
    }

    public MaxHeap(E[] array) {
        this.array = new Array(array);
        for (int k = parent(this.array.getSize() - 1); k >= 0; k--) {
            shiftDown(k);
        }
    }

    public MaxHeap() {
        this(10);
    }

    @Override
    public int getSize() {
        return this.array.getSize();
    }

    @Override
    public boolean isEmpty() {
        return this.array.isEmpty();
    }

    @Override
    public void add(E e) {
        this.array.addLast(e);
        shiftUp(array.getSize() - 1);
    }

    @Override
    public E extract() {
        E e = array.get(0);
        array.swap(0, array.getSize() - 1);
        array.removeLast();
        shiftDown(0);
        return e;
    }


    //返回父节点索引
    private int parent(int index) {
        if (index == 0) {
            throw new IllegalArgumentException("index-0 doesn't have parent.");
        }
        return index / 2;
    }

    //返回左孩子节点索引
    private int left(int index) {
        return 2 * index + 1;
    }

    //返回右孩子节点索引
    private int right(int index) {

        return 2 * index + 2;
    }

    private void shiftUp(int index) {
        //有父节点
        while (index > 0 && array.get(index).compareTo(array.get(parent(index))) > 0) {
            array.swap(index, parent(index));
            index = parent(index);
        }
    }

    private void shiftDown(int index) {
        //有左孩子
        while (left(index) < array.getSize()) {
            int j = left(index);
            if (j + 1 < array.getSize() && array.get(j).compareTo(array.get(j + 1)) < 0) {
                j = j + 1;
            }
            if (array.get(index).compareTo(array.get(j)) >= 0) {
                break;
            }
            array.swap(j, index);
            index = j;
        }
    }

    public static void main(String[] args) {
        int[] ints = SortTestHelper.generateRandomArray(10, 1, 10);
        System.out.println(Arrays.toString(ints));
        MaxHeap heap = new MaxHeap();
        for (int i = 0; i < ints.length; i++) {
            heap.add(ints[i]);
        }
        System.out.println();
        while (!heap.isEmpty()) {
            System.out.print(heap.extract() + " ");
        }
    }
}
