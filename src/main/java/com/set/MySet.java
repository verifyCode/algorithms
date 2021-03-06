package com.set;

/**
 * @author xjn
 * @since 2019-12-25
 */
public interface MySet<E> {
    void add(E e);

    void remove(E e);

    boolean contains(E e);

    int getSize();

    boolean isEmpty();
}
