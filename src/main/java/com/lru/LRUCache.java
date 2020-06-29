package com.lru;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * @author xjn
 * @since 2020-02-28
 */
public class LRUCache {

    class DoubleLinkedList {
        int key;
        int value;
        DoubleLinkedList prev;
        DoubleLinkedList next;

        public DoubleLinkedList() {
        }

        public DoubleLinkedList(int key, int value) {
            this.key = key;
            this.value = value;
        }
    }

    private int size;
    private int capacity;
    private DoubleLinkedList head, tail;
    private Map<Integer, DoubleLinkedList> map = new HashMap<>();

    public LRUCache(int capacity) {
        this.size = 0;
        this.capacity = capacity;
        this.head = new DoubleLinkedList();
        this.tail = new DoubleLinkedList();
        head.next = tail;
        tail.prev = head;
    }

    public int get(int key) {
        DoubleLinkedList node = this.map.get(key);
        if (Objects.isNull(node)) {
            return -1;
        }
        moveToHead(node);
        return node.value;
    }

    public void put(int key, int value) {
        DoubleLinkedList node = map.get(key);
        // 如果 key 不存在，创建一个新的节点
        if (Objects.isNull(node)) {
            DoubleLinkedList newNode = new DoubleLinkedList(key, value);
            // 添加进哈希表
            map.put(key, newNode);
            // 添加至双向链表的头部
            addToHead(newNode);
            size++;
            if (size > capacity) {
                // 如果超出容量，删除双向链表的尾部节点
                DoubleLinkedList tail = removeTail();
                map.remove(tail.key);
                size--;
            }
        } else {
            node.value = value;
            moveToHead(node);
        }
    }

    private void addToHead(DoubleLinkedList node) {
        node.prev = head;
        node.next = head.next;
        head.next = node;
        head.next.prev = node;
    }

    private void removeNode(DoubleLinkedList node) {
        node.prev.next = node.next;
        node.next.prev = node.prev;
    }

    private void moveToHead(DoubleLinkedList node) {
        removeNode(node);
        addToHead(node);
    }

    private DoubleLinkedList removeTail() {
        DoubleLinkedList res = tail.prev;
        removeNode(res);
        return res;
    }


}
