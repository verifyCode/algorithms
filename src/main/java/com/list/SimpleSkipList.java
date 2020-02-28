package com.list;

import java.util.Random;

/**
 * @author xjn
 * @since 2020-02-23
 */
public class SimpleSkipList {

    private final static byte HEAD_NODE = (byte) -1;
    private final static byte DATA_NODE = (byte) 0;
    private final static byte TAIL_NODE = (byte) 1;

    private static class Node {
        private Integer value;
        private Node up, down, left, right;
        private byte flag;

        public Node(Integer value, byte flag) {
            this.value = value;
            this.flag = flag;
        }

        public Node(Integer value) {
            this(value, DATA_NODE);
        }
    }

    private Node head;
    private Node tail;
    private int size;
    private int height;
    private Random random;

    public SimpleSkipList() {
        this.head = new Node(null, HEAD_NODE);
        this.tail = new Node(null, TAIL_NODE);
        head.left = tail;
        tail.right = head;
        this.random = new Random(System.currentTimeMillis());
    }

    private Node find(Integer element) {
        Node current = head;
        for (; ; ) {
            while (current.right.flag != TAIL_NODE && current.right.value < element) {
                current = current.right;
            }
            if (current.down != null) {
                current = current.down;
            } else {
                break;
            }
        }
        //current.value <= element < current.right(if exist)
        return current;
    }

    public boolean contains(Integer element) {
        Node node = this.find(element);
        return element == node.value;
    }

    public boolean isEmpty() {
        return this.size == 0;
    }

    public int getSize() {
        return this.size;
    }


}
