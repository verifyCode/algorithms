package com.map;

public interface Tree<K extends Comparable, V> {

    boolean isEmpty();

    int getSize();

    void add(K key, V value);

    boolean contains(K key);

    //二叉树的前序遍历实际上是深度优先遍历
    void preOrder();

    void inOrder();

    void postOrder();

    void bfs();

    void dfs();

    int getTreeDepth();

    V maximum();

    V minimum();

    V removeMaximum();

    V removeMinimum();

    void remove(K k);
}
