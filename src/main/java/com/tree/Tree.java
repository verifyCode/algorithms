package com.tree;


/**
 * @author xjn
 * @since 2019-12-24
 */
public interface Tree<E extends Comparable> {

    boolean isEmpty();

    int getSize();

    void add(E e);

    boolean contains(E e);

    void preOrder();

    void inOrder();

    void postOrder();

    void bfs();

    void dfs();

    int getTreeDepth();

    E maximum();

    E minimum();

    E removeMaximum();

    E removeMinimum();

}
