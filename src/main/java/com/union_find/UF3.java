package com.union_find;

/**
 * @author xjn
 * @since 2019-12-12
 */
public class UF3 {
    private int[] parent;
    private int count;
    private int[] rank;//rank[i] 表示以i为根元素的层数

    public UF3(int count) {
        this.parent = new int[count];
        this.count = count;
        this.rank = new int[count];
        for (int i = 0; i < rank.length; i++) {
            rank[i] = 1;
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
        if (rank[pRoot] < rank[qRoot]) {
            parent[pRoot] = qRoot;
        } else if (rank[pRoot] > rank[qRoot]) {
            parent[qRoot] = pRoot;
        } else {
            parent[qRoot] = pRoot;
            rank[qRoot] += 1;
        }
    }
}
