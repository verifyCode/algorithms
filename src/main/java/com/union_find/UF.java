package com.union_find;

/**
 * @author xjn
 * @since 2019-12-12
 */
public class UF {
    private int[] parent;
    private int count;

    public UF(int count) {
        parent = new int[count];
        this.count = count;
    }

    public int find(int p) {
        while (p != parent[p]) {
            p = parent[p];
        }
        return p;
    }

    public boolean isConnected(int p, int q) {
        return find(p) == find(q);
    }

    public void union(int p, int q) {
        int pRoot = find(p);
        int qRoot = find(q);
        if (pRoot == qRoot) {
            return;
        }
        parent[pRoot] = qRoot;
    }
}
