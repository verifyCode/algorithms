package com.heap_priority_queue;

/**
 * @author xjn
 * @since 2019-12-26
 */
public interface Heap<E extends Comparable> {
    int getSize();

    boolean isEmpty();

    void add(E e);

    E extract();
}
