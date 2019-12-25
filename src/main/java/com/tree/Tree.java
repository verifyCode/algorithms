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

    //二叉树的前序遍历实际上是深度优先遍历
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

    void remove(E e);

}
