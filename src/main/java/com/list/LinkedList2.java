package com.list;

/**
 * LinkedList with dummyHead
 *
 * @author xjn
 * @since 2019-12-17
 */
public class LinkedList2<E> implements List<E> {

    private class Node {
        private E e;
        private Node next;

        public Node(E e, Node node) {
            this.e = e;
            this.next = node;
        }

        public Node(E e) {
            this(e, null);
        }

        public Node() {
            this(null, null);
        }

        @Override
        public String toString() {
            return e.toString();
        }
    }

    private Node dummyHead;
    private int size;

    public LinkedList2() {
        dummyHead = new Node();
        size = 0;
    }

    @Override
    public int getSize() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public void add(E e, int index) {
        checkIndex(index);
        Node prev = dummyHead;
        for (int i = 0; i < index; i++) {
            prev = prev.next;
        }
        prev.next = new Node(e, prev.next);
    }

    @Override
    public void addFirst(E e) {
        add(e, 0);
    }

    @Override
    public void addLast(E e) {
        add(e, size);
    }

    @Override
    public E get(int index) {
        checkIndex(index);
        Node cur = dummyHead.next;
        for (int i = 0; i < index; i++) {
            cur = cur.next;
        }
        return cur.e;
    }

    @Override
    public E getFirst() {
        return get(0);
    }

    @Override
    public E getLast() {
        return get(size - 1);
    }

    @Override
    public void set(E e, int index) {
        checkIndex(index);
        Node cur = dummyHead.next;
        for (int i = 0; i < index; i++) {
            cur = cur.next;
        }
        cur.e = e;
    }

    @Override
    public boolean contains(E e) {
        Node cur = dummyHead.next;
        while (cur != null) {
            if (e.equals(cur)) {
                return true;
            }
            cur = cur.next;
        }
        return false;
    }

    @Override
    public E remove(int index) {
        return null;
    }

    @Override
    public E removeFirst() {
        return null;
    }

    @Override
    public E removeLast() {
        return null;
    }

    @Override
    public void removeElement(E e) {

    }

    @Override
    public String toString() {
        StringBuilder res = new StringBuilder();

//        Node cur = dummyHead.next;
//        while(cur != null){
//            res.append(cur + "->");
//            cur = cur.next;
//        }
        for (Node cur = dummyHead.next; cur != null; cur = cur.next)
            res.append(cur + "->");
        res.append("NULL");

        return res.toString();
    }

    private void checkIndex(int index) {
        if (index < 0 && index > size) {
            throw new IllegalArgumentException("Error index");
        }
    }
}
