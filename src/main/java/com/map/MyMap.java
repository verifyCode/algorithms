package com.map;

/**
 * @author xjn
 * @since 2019-12-26
 */
public interface MyMap<K, V> {
    void add(K key, V value);

    V remove(K key);

    boolean contains(K key);

    V get(K key);

    void set(K key, V newValue);

    int getSize();

    boolean isEmpty();


}
