package com.list;

/**
 * @author xjn
 * @since 2019-12-17
 */
public interface List<E> {
    int getSize();

    boolean isEmpty();

    void add(E e, int index);

    void addFirst(E e);

    void addLast(E e);

    E get(int index);

    E getFirst();

    E getLast();

    void set(E e, int index);

    boolean contains(E e);

    E remove(int index);

    E removeFirst();

    E removeLast();

    void removeElement(E e);


}
