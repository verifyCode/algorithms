package com.stack_and_queue;

import com.list.LinkedList2;

/**
 * @author xjn
 * @since 2019-12-17
 */
public class LinkedListQueue<E> implements Queue<E> {
    private LinkedList2<E> linkedList2;

    public LinkedListQueue() {
        linkedList2 = new LinkedList2<>();
    }

    @Override
    public int getSize() {
        return 0;
    }

    @Override
    public void enQueue(E e) {

    }

    @Override
    public E deQueue() {
        return null;
    }

    @Override
    public E getFront() {
        return null;
    }

    @Override
    public boolean isEmpty() {
        return false;
    }
}
