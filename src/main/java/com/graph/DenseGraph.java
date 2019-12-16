package com.graph;

import java.util.Arrays;
import java.util.Vector;

/**
 * 稠密图-邻接矩阵
 *
 * @author xjn
 * @since 2019-12-12
 */
public class DenseGraph implements Graph {
    /*** 点数 ***/
    private int n;
    /*** 边数 ***/
    private int m;

    /*** 是否有向图 ***/
    private boolean directed;

    /*** 图的具体数据 ***/
    private boolean g[][];

    public DenseGraph(int n, boolean isDirected) {
        this.n = n;
        this.m = 0;
        this.directed = isDirected;
        this.g = new boolean[n][n];
    }

    //多少点
    public int V() {
        return n;
    }

    //多少边
    public int E() {
        return m;
    }

    public void addEdge(int v, int w) {
        //已经存在边
        if (hasEdge(v, w)) {
            return;
        }
        this.g[v][w] = true;
        if (directed) {
            this.g[w][v] = true;
        }
        m++;
    }

    public Iterable<Integer> iterator(int v) {
        Vector<Integer> vector = new Vector<>();
        for (int i = 0; i < n; i++) {
            if (g[v][i]) {
                vector.add(i);
            }
        }
        return vector;
    }

    public boolean hasEdge(int v, int w) {
        return this.g[v][w];
    }

    public void show() {
        System.out.println(Arrays.deepToString(g));
    }
}
