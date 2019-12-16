package com.graph;

import java.util.Iterator;

/**
 * @author xjn
 * @since 2019-12-14
 */
public interface Graph {
    //有多少点
    int V();

    //有多少边
    int E();

    //添加边
    void addEdge(int v, int w);

    //两点是否有边
    boolean hasEdge(int v, int w);

    Iterable<Integer>  iterator(int v);
}
