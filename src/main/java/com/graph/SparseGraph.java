package com.graph;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;
import java.util.Vector;

/**
 * 稀疏图--邻接表
 *
 * @author xjn
 * @since 2019-12-12
 */
public class SparseGraph implements Graph {

    /*** 节点数 ***/
    private int n;

    /*** 边数 ***/
    private int m;

    /*** 是否为有向图 ***/
    private boolean directed;

    /*** 图的具体数据 ***/
    private Vector<Integer>[] g;

    public SparseGraph(int n, boolean directed) {
        this.m = 0;
        this.n = n;
        this.directed = directed;
        this.g = new Vector[n];
        for (int i = 0; i < n; i++) {
            ArrayList arrayList = new ArrayList();
            this.g[i] = new Vector<>();
        }
    }

    public int V() {
        return n;
    }

    public int E() {
        return m;
    }

    //向图中添加一条边
    public void addEdge(int v, int w) {
        g[v].add(w);

        if (v != w && directed) {
            g[w].add(v);
        }
        m++;
    }

    public void show() {
        for (int i = 0; i < g.length; i++) {
            System.out.println("vertex " + i + " :" + g[i]);
        }
    }

    /*** 验证是否有从v到w的边 ***/
    public boolean hasEdge(int v, int w) {
        return g[v].contains(w);
    }

    public Iterable<Integer> iterator(int v) {
        return g[v];
    }

    public static void main(String[] args) {
        int n = 20;
        int m = 100;
        //20个点 100条边
        SparseGraph sparseGraph = new SparseGraph(n, false);
        DenseGraph denseGraph = new DenseGraph(n, false);
        Random random = new Random(System.currentTimeMillis());
        for (int i = 0; i < m; i++) {
            int a = random.nextInt(n);
            int b = random.nextInt(n);
            sparseGraph.addEdge(a, b);
            denseGraph.addEdge(a, b);
        }
        for (int i = 0; i < n; i++) {
            Iterator<Integer> iterator = sparseGraph.iterator(i).iterator();
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("i:").append(i + " ");
            while (iterator.hasNext()) {
                Object next = iterator.next();
                stringBuilder.append(next + " ");
            }
            System.out.println(stringBuilder.toString());
        }
        System.out.println("=============");
        for (int i = 0; i < n; i++) {
            StringBuilder builder = new StringBuilder();
            builder.append("i:").append(i + " ");
            Iterator<Integer> iterator = denseGraph.iterator(i).iterator();
            while (iterator.hasNext()) {
                Integer next = (int) iterator.next();
                builder.append(next + " ");
            }
            System.out.println(builder.toString());
        }
    }
}
