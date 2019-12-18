package com.stack_and_queue;

import com.list.LinkedList2;

/**
 * @author xjn
 * @since 2019-12-17
 */
public class LinkedListStack<E> implements Stack<E> {
    private LinkedList2<E> linkedList;

    public LinkedListStack(int capacity) {
        linkedList = new LinkedList2();
    }

    @Override
    public void push(E e) {
        linkedList.addFirst(e);
    }

    @Override
    public E pop() {
        return linkedList.removeFirst();
    }

    @Override
    public E peek() {
        return linkedList.getFirst();
    }

    @Override
    public int getSize() {
        return linkedList.getSize();
    }

    @Override
    public boolean isEmpty() {
        return linkedList.isEmpty();
    }

    @Override
    public String toString() {
        StringBuilder res = new StringBuilder();
        res.append("Stack: top ");
        res.append(linkedList);
        return res.toString();
    }
}
