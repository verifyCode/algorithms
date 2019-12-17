package com.list;

/**
 * @author xjn
 * @since 2019-12-16
 */
public class LinkedList<E> implements List<E> {

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

        @Override
        public String toString() {
            return e.toString();
        }
    }


    private Node head;
    private int size;

    public LinkedList() {
        this.head = null;
        this.size = 0;
    }

    /*** 获取链表中的元素个数***/
    public int getSize() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    //在链表表头添加元素e
    public void addFirst(E e) {
//        Node node = new Node(e);
//        node.next = head;
//        head = node;

        //第二种写法
        head = new Node(e, head);
        size++;
    }

    //在链表index(0-based)位置添加新的元素e
    public void add(E e, int index) {
        checkIndex(index);
        if (index == 0) {
            addFirst(e);
        } else {
            Node prev = head;
            for (int i = 0; i < index - 1; i++) {
                prev = prev.next;
            }
//            Node node = new Node(e);
//            node.next = prev.next;
//            prev.next = node;
            prev.next = new Node(e, prev.next);
            size++;
        }
    }

    //末尾添加新的元素
    public void addLast(E e) {
        add(e, size);
    }

    @Override
    public E get(int index) {
        checkIndex(index);
        Node cur = head;
        for (int i = 0; i < index; i++) {
            cur = cur.next;
        }
        return head.e;
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
        Node cur = head;
        for (int i = 0; i < index; i++) {
            cur = cur.next;
        }
        cur.e = e;
    }

    @Override
    public boolean contains(E e) {
        Node cur = head;
        while (cur != null) {
            if (cur.e.equals(e)) {
                return true;
            }
            cur = cur.next;
        }
        return false;
    }

    @Override
    public E remove(int index) {
        checkIndex(index);
        if (index == 0) {
            return removeFirst();
        }
        Node cur = head;
        for (int i = 0; i < index; i++) {
            cur = cur.next;
        }
        Node retNode = cur.next;
        cur.next = retNode.next;
        retNode.next = null;
        size--;
        return retNode.e;
    }

    @Override
    public E removeFirst() {
        Node delNode = head;
        E ret = delNode.e;
        head = delNode.next;
        delNode = null;
        return ret;
    }

    @Override
    public E removeLast() {
        return remove(size - 1);
    }

    @Override
    public void removeElement(E e) {
        int size = this.size;
        Node cur = head;
        int i;
        for (i = 0; i < size; i++) {
            if (e.equals(cur.e)) {
                break;
            }
            cur = cur.next;
        }
        remove(i);
    }

    @Override
    public String toString() {
        StringBuilder res = new StringBuilder();

//        Node cur = cur;
//        while(cur != null){
//            res.append(cur + "->");
//            cur = cur.next;
//        }
        for (Node cur = head; cur != null; cur = cur.next)
            res.append(cur.e + "->");
        res.append("NULL");
        return res.toString();
    }

    private void checkIndex(int index) {
        if (index < 0 && index > size) {
            throw new IllegalArgumentException("Error index");
        }
    }


}
