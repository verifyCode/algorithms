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
        return linkedList2.getSize();
    }

    @Override
    public void enQueue(E e) {
        linkedList2.addLast(e);
    }

    @Override
    public E deQueue() {
        return linkedList2.removeFirst();
    }

    @Override
    public E getFront() {
        return linkedList2.getFirst();
    }

    @Override
    public boolean isEmpty() {
        return linkedList2.isEmpty();
    }
}
