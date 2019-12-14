package com.graph;

/**
 * 稠密图-邻接矩阵
 *
 * @author xjn
 * @since 2019-12-12
 */
public class DenseGraph {
    /*** 点数 ***/
    private int n;
    /*** 边数 ***/
    private int m;

    private boolean directed;

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

    public boolean hasEdge(int v, int w) {
        return this.g[v][w];
    }

}
