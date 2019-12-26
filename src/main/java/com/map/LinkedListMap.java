package com.map;

/**
 * @author xjn
 * @since 2019-12-26
 */
public class LinkedListMap<K, V> implements MyMap<K, V> {
    private LinkedList<K, V> linkedList;

    public LinkedListMap() {
        this.linkedList = new LinkedList<>();
    }

    @Override
    public void add(K key, V value) {
        if (linkedList.contains(key)) {
            linkedList.set(key, value);
        } else {
            linkedList.add(key, value);
        }
    }

    @Override
    public V remove(K key) {
        return linkedList.remove(key);
    }

    @Override
    public boolean contains(K key) {
        return linkedList.contains(key);
    }

    @Override
    public V get(K key) {
        return linkedList.get(key);
    }

    @Override
    public void set(K key, V newValue) {
        if (linkedList.contains(key)) {
            linkedList.set(key, newValue);
        } else {
            linkedList.add(key, newValue);
        }
    }

    @Override
    public int getSize() {
        return linkedList.getSize();
    }

    @Override
    public boolean isEmpty() {
        return linkedList.isEmpty();
    }
}
