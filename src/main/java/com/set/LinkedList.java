package com.set;

import com.list.LinkedList3;

/**
 * @author xjn
 * @since 2019-12-25
 */
public class LinkedList<E> implements MySet<E> {

    private LinkedList3<E> linkedList;

    public LinkedList() {
        linkedList = new LinkedList3<>();
    }

    //O(n)
    @Override
    public void add(E e) {
        if (!contains(e)) {
            linkedList.addFirst(e);
        }
    }

    //O(n)
    @Override
    public void remove(E e) {
        linkedList.removeElement(e);
    }

    //O(n)
    @Override
    public boolean contains(E e) {
        return linkedList.contains(e);
    }

    //O(1)
    @Override
    public int getSize() {
        return linkedList.getSize();
    }

    //O(1)
    @Override
    public boolean isEmpty() {
        return linkedList.isEmpty();
    }
}
