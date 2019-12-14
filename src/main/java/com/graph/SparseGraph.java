package com.graph;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * @author xjn
 * @since 2019-12-12
 */
public class SparseGraph {

    private int n;
    private int m;
    private List<Integer>[] g;
    private boolean directed;

    public SparseGraph(int n, boolean directed) {
        this.m = 0;
        this.n = n;
        this.directed = directed;
        this.g = new ArrayList[n];
    }

    public int V() {
        return n;
    }

    public int E() {
        return m;
    }

    public void addEdge(int v, int w) {
        g[v].add(w);
        if (v != w && directed) {
            g[w].add(v);
        }
        m++;
    }

    public boolean hasEdge(int v, int w) {
        return g[v].contains(w);
    }

    class AdjIterator implements Iterator {
        private SparseGraph sparseGraph;
        //迭代哪个定点所对应的边
        private int v;

        private int index;

        public AdjIterator(SparseGraph sparseGraph, int v) {
            this.sparseGraph = sparseGraph;
            this.v = v;
            this.index = 0;
        }

        public int begin() {
            if (sparseGraph.g[v].size() == 0) {
                return -1;
            }
            return sparseGraph.g[v].get(0);
        }

        @Override
        public boolean hasNext() {
            return index >= sparseGraph.g[v].size();
        }

        @Override
        public Object next() {
            if (index < sparseGraph.g[v].size()) {
                index++;
                return sparseGraph.g[v].get(index);
            }
            return sparseGraph.g[v].get(index);
        }
    }
}
