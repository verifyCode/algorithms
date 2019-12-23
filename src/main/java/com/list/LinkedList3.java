package com.list;

/**
 * 链表与递归
 *
 * @author xjn
 * @since 2019-12-17
 */
public class LinkedList3<E> implements List<E> {
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

    public LinkedList3() {
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
        size++;
        addRecursive(dummyHead,e,index);
    }

    private Node addRecursive(Node head, E e, int index) {
        if (head == null) {
            return null;
        }
        if (index == 0) {
            Node temp = head.next;
            Node node = new Node(e);
            head.next = node;
            node.next = temp;
        }
        return addRecursive(head.next, e, index - 1);
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
        return getRecursive(dummyHead, index);
    }

    private E getRecursive(Node node, int index) {
        if (node == null) {
            return null;
        }
        if (index == 0) {
            return node.e;
        }
        return getRecursive(node.next, index - 1);
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
        setRecursive(dummyHead, e, index + 1);
    }

    private Node setRecursive(Node node, E e, int index) {
        if (node == null) {
            return null;
        }
        if (index == 0) {
            node.e = e;
            return node;
        }
        return setRecursive(node.next, e, index - 1);
    }

    @Override
    public boolean contains(E e) {
        return containsRecursive(dummyHead, e) != null;
    }

    private E containsRecursive(Node node, E e) {
        if (node == null) {
            return null;
        }
        if (node.e == e) {
            return e;
        }
        return containsRecursive(node.next, e);
    }

    @Override
    public E remove(int index) {
        checkIndex(index);
        size--;
        return removeRecursive(dummyHead, index).e;
    }

    private Node removeRecursive(Node node, int index) {
        if (node == null) {
            return null;
        }
        if (node.next == null) {
            return node;
        }
        if (index == 0) {
            node.next = node.next.next;
        }
        return removeRecursive(node.next, index - 1);

    }

    @Override
    public E removeFirst() {
        return remove(0);
    }

    @Override
    public E removeLast() {
        return remove(size - 1);
    }

    @Override
    public void removeElement(E e) {
        removeElementRecursive(dummyHead, e);
    }

    private Node removeElementRecursive(Node head, E e) {
        if (head == null) {
            return null;
        }
        Node res = removeElementRecursive(head.next, e);
        if (head.e == e) {
            return res;
        } else {
            head.next = res;
        }
        return head;
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
