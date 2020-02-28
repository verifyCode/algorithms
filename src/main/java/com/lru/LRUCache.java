package com.lru;

import java.util.Map;

/**
 * @author xjn
 * @since 2020-02-28
 */
public class LRUCache {

    private static class DoubleLinkedList {
        private int value;
        private int key;
        private DoubleLinkedList pre;
        private DoubleLinkedList post;
    }

    private int size;
    private int capacity;
    private DoubleLinkedList head, tail;
    private Map<Integer, DoubleLinkedList> map;

    public LRUCache(int capacity) {
        this.size = 0;
        this.capacity = capacity;
        head = new DoubleLinkedList();
        head.pre = null;
        tail.post = null;
        head.post = tail;
        tail.pre = head;
    }


    private void moveToHead(DoubleLinkedList node) {
        removeNode(node);
        addNode(node);
    }

    private void addNode(DoubleLinkedList node) {
        node.pre = head;
        node.post = head.post;
        head.post.pre = node;
        head.post = node;
    }

    private void removeNode(DoubleLinkedList node) {
        DoubleLinkedList pre = node.pre;
        DoubleLinkedList post = node.post;

        pre.post = post;
        post.pre = pre;

    }

    public int get(int key) {
        DoubleLinkedList node = map.get(key);
        //么有命中
        if (node == null) {
            return -1;
        }
        //命中,挪到开头
        moveToHead(node);
        return node.value;
    }

    public DoubleLinkedList popTail() {
        DoubleLinkedList res = tail.pre;
        removeNode(res);
        return res;
    }

    public void put(int key, int value) {
        DoubleLinkedList node = map.get(key);
        if (node == null) {
            node = new DoubleLinkedList();
            node.value = value;
            addNode(node);
            size++;
            if (size > capacity) {
                DoubleLinkedList tail = this.popTail();
                map.remove(tail.key);
                size--;
            }
        } else {
            node.value = value;
            moveToHead(node);
        }
    }

}
