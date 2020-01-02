package com.tree.segment_tree;

/**
 * @author xjn
 * @since 2019-12-28
 */
public interface Merger<E> {
    E merge(E a, E b);
}
