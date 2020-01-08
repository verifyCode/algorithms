package com.map;

import java.util.TreeMap;

/**
 * @author xjn
 * @since 2020-01-07
 */
public class HashTable<K, V> implements MyMap<K, V> {

    private final static int initCapacity = 4;

    private final static int upperTol = 10;

    private final static int lowTol = 5;

    private TreeMap<K, V> table[];

    private int size;

    private int length;


    public HashTable(int length) {
        this.length = length;
        this.table = new TreeMap[length];
        for (int i = 0; i < length; i++) {
            table[i] = new TreeMap<>();
        }
        this.size = 0;
    }

    public HashTable() {
        this(initCapacity);
    }

    public int tableSizeFor(int length) {
        int q = 0;
        for (; length > 0; length >>= 1) {
            q++;
        }
        return 1 << q;
    }


    private int hash(Object key) {
        return key.hashCode() % this.length;
    }


    @Override
    public void add(K key, V value) {
        TreeMap treeMap = this.table[hash(key)];
        if (treeMap.containsKey(key)) {
            set(key, value);
        } else {
            if (this.size >= upperTol * this.length) {
                resize(this.length * 2);
            }
            treeMap.put(key, value);
            size++;
        }
    }

    @Override
    public V remove(K key) {
        V ret = null;
        TreeMap<K, V> treeMap = table[hash(key)];
        ret = treeMap.get(key);
        if (ret != null) {
            treeMap.remove(ret);
            size--;
            if (this.size < lowTol * length && this.length / 2 > initCapacity) {
                resize(this.length / 2);
            }
        }
        return ret;
    }

    @Override
    public boolean contains(K key) {
        return get(key) != null;
    }

    @Override
    public V get(K key) {
        TreeMap<K, V> treeMap = this.table[hash(key)];
        return treeMap.get(key);
    }

    @Override
    public void set(K key, V newValue) {
        TreeMap treeMap = this.table[hash(key)];
        if (!treeMap.containsKey(key)) {
            throw new IllegalArgumentException(key + " doesn't exist!");
        } else {
            treeMap.put(key, newValue);
        }
    }

    @Override
    public int getSize() {
        return this.size;
    }

    @Override
    public boolean isEmpty() {
        return this.size == 0;
    }

    private void resize(float capacity) {
//        int length = tableSizeFor((int) capacity);
        int length = (int) capacity;
        TreeMap<K, V> newTables[] = new TreeMap[length];
        for (int i = 0; i < length; i++) {
            newTables[i] = new TreeMap<>();
        }
        int oldLength = this.length;
        this.length = length;
        for (int i = 0; i < oldLength; i++) {
            TreeMap<K, V> treeMap = table[i];
            for (K key : treeMap.keySet()) {
                newTables[hash(key)].put(key, treeMap.get(key));
            }
        }
        this.table = newTables;
    }

    public static void main(String[] args) {
        HashTable hashTable = new HashTable();
        int n = 100;
        for (int i = 0; i < n; i++) {
            hashTable.add(i, i);
        }
        hashTable.get(100);
        for (int i = 0; i < 99; i++) {
            hashTable.remove(i);
        }
        hashTable.get(1);
    }
}
