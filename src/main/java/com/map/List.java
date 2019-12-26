package com.map;

/**
 * @author xjn
 * @since 2019-12-26
 */
public interface List<K, V> {
    int getSize();

    boolean isEmpty();

    void add(K key, V value);

    void addFirst(K key, V value);

    void addLast(K key, V value);

    V get(K Key);

    V getFirst();

    V getLast();

    void set(K key, V value);

    boolean contains(K key);

    V remove(K key);

    V removeFirst();

    V removeLast();

    void removeElement(K k);
}
