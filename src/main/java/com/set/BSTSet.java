package com.set;

import com.tree.bst.BST;

/**
 * 基于二分搜索树
 *
 * @author xjn
 * @since 2019-12-25
 */
public class BSTSet<E extends Comparable<E>> implements MySet<E> {

    private BST<E> bst;

    public BSTSet() {
        bst = new BST<>();
    }

    //O(h) h:二分搜索树的高度
    // 0层1个节点
    // 1层2个节点
    // 2层4个节点
    // 3层8个节点
    // 4层16个节点
    // h层2^(h-1)个节点
    // h层全部多少节点 = 2^h - 1
    // n = 2^h -1
    // h = log2(n + 1)
    //O(h) = O(log n)
    @Override
    public void add(E e) {
        bst.add(e);
    }

    //O(h) h:二分搜索树的高度
    //O(h) = O(log2)
    @Override
    public void remove(E e) {
        bst.remove(e);
    }

    //O(h) = O(log2)
    @Override
    public boolean contains(E e) {
        return bst.contains(e);
    }

    //O(1)
    @Override
    public int getSize() {
        return bst.getSize();
    }
    //O(1)
    @Override
    public boolean isEmpty() {
        return bst.isEmpty();
    }
}
