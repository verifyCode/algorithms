package com.tree.set;

import com.list.LinkedList3;

/**
 * @author xjn
 * @since 2019-12-25
 */
public class LinkedListSet<E> implements Set<E> {

    private LinkedList3<E> linkedList;

    public LinkedListSet() {
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
