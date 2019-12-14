package com.union_find;

/**
 * @author xjn
 * @since 2019-12-12
 */
public class UF2 {
    private int[] parent;
    private int count;
    private int[] sz;//sz[i] 表示以i为根元素的个数

    public UF2(int count) {
        parent = new int[count];
        this.count = count;
        sz = new int[count];
        for (int i = 0; i < sz.length; i++) {
            sz[i] = 1;
        }
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
        //当两棵树合并的时候,用元素少的作为根
        if (sz[pRoot] < sz[qRoot]) {
            parent[pRoot] = qRoot;
            sz[qRoot] += sz[pRoot];
        } else {
            parent[pRoot] = pRoot;
            sz[pRoot] += sz[pRoot];
        }
    }
}
