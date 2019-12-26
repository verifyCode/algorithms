package com.map;

/**
 * @author xjn
 * @since 2019-12-26
 */
public class BSTMap<K extends Comparable, V> implements MyMap<K, V> {
    private BST bst;

    public BSTMap() {
        bst = new BST();
    }

    @Override
    public void add(K key, V value) {
        bst.add(key, value);
    }

    @Override
    public V remove(K key) {
        return null;
    }

    @Override
    public boolean contains(K key) {
        return false;
    }

    @Override
    public V get(K key) {
        return null;
    }

    @Override
    public void set(K key, V newValue) {

    }

    @Override
    public int getSize() {
        return 0;
    }

    @Override
    public boolean isEmpty() {
        return false;
    }
}
