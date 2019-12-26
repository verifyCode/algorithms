package com.map;


import com.list.LinkedList3;

/**
 * @author xjn
 * @since 2019-12-26
 */
public class LinkedList<K, V> implements List<K, V> {
    private class Node {
        public K key;
        public V value;
        public Node next;

        public Node(K key, V value) {
            this.key = key;
            this.value = value;
            this.next = null;
        }

        public Node() {
            this.key = null;
            this.value = null;
            this.next = null;
        }

        @Override
        public String toString() {
            return "(" + key + "," + value + ")";
        }
    }

    private Node dummyHead;
    private int size;

    public LinkedList() {
        this.dummyHead = new Node();
        this.size = 0;
    }

    @Override
    public int getSize() {
        return this.size;
    }

    @Override
    public boolean isEmpty() {
        return this.size == 0;
    }

    @Override
    public void add(K key, V value) {
        addRecursive(dummyHead, key, value);
    }

    private Node addRecursive(Node node, K key, V value) {
        if (node.next == null) {
            Node temp = new Node(key, value);
            node.next = temp;
            size++;
            return node;
        }
        return addRecursive(node.next, key, value);
    }

    @Override
    public void addFirst(K key, V value) {
        Node temp = dummyHead.next;
        Node node = new Node(key, value);
        dummyHead.next = node;
        node.next = temp;
    }

    @Override
    public void addLast(K key, V value) {
        Node cur = dummyHead.next;
        while (cur.next != null) {
            cur = cur.next;
        }
        Node node = new Node(key, value);
        cur.next = node;
    }

    @Override
    public V get(K key) {
        Node cur = dummyHead.next;
        while (cur != null) {
            if (key.equals(cur.key)) {
                return cur.value;
            }
            cur = cur.next;
        }
        return null;
    }

    @Override
    public V getFirst() {
        Node cur = dummyHead.next;
        if (cur != null) {
            return cur.value;
        }
        return null;
    }

    @Override
    public V getLast() {
        Node cur = dummyHead.next;
        while (cur.next != null) {
            cur = cur.next;
        }
        return cur.value;
    }

    @Override
    public void set(K key, V value) {
        Node cur = dummyHead.next;
        while (cur != null) {
            if (key.equals(key)) {
                cur.value = value;
            }
            cur = cur.next;
        }
    }

    @Override
    public boolean contains(K key) {
        Node cur = dummyHead.next;
        while (cur != null) {
            if (key.equals(key)) {
                return true;
            }
            cur = cur.next;
        }
        return false;
    }

    @Override
    public V remove(K key) {
        if (isEmpty()) {
            return null;
        }
        V v = get(key);
        removeRecursive(dummyHead, key);
        size--;
        return v;
    }

    private Node removeRecursive(Node node, K key) {
        if (node == null) {
            return null;
        }
        Node res = removeRecursive(node.next, key);
        if (key.equals(node.key)) {
            return res;
        } else {
            node.next = res;
        }
        return node;
    }

    @Override
    public V removeFirst() {
        if (isEmpty()) {
            return null;
        }
        V value = dummyHead.next.value;
        dummyHead.next = dummyHead.next.next;
        size--;
        return value;
    }

    @Override
    public V removeLast() {
        if (isEmpty()) {
            return null;
        }
        Node cur = dummyHead;
        for (int i = 0; i < size - 1; i++) {
            cur = cur.next;
        }
        cur.next = null;
        return null;
    }

    @Override
    public void removeElement(K k) {
        removeRecursive(dummyHead, k);
    }

    @Override
    public String toString() {
        StringBuilder res = new StringBuilder();

//        Node cur = dummyHead.next;
//        while(cur != null){
//            res.append(cur + "->");
//            cur = cur.next;
//        }
        res.append("dummyHead->");
        for (Node cur = dummyHead.next; cur != null; cur = cur.next)
            res.append(cur + "->");
        res.append("NULL");

        return res.toString();
    }


    public static void main(String[] args) {
        LinkedList<Integer, Integer> linkedList = new LinkedList<>();
        linkedList.removeFirst();
        for (int i = 0; i < 10; i++) {
            linkedList.add(i, i);
        }
        System.out.println(linkedList);
//        System.out.println(linkedList.removeFirst());
        linkedList.removeLast();
        System.out.println(linkedList);
    }
}
