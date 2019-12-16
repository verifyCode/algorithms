package com.graph;

/**
 * @author xjn
 * @since 2019-12-14
 */
public class Components {
    private Graph graph;
    //是否被访问过
    boolean[] visited;
    //有多少联通分量
    private int count;
    private int[] id;

    public Components(Graph graph) {
        this.graph = graph;
        this.visited = new boolean[graph.V()];
        this.count = 0;
        this.id = new int[graph.V()];
        for (int i = 0; i < graph.V(); i++) {
            id[i] = -1;
            if (!visited[i]) {
                dfs(i);
                count++;
            }
        }
    }

    private void dfs(int v) {
        visited[v] = true;
        id[v] = count;
        for (int i : graph.iterator(v)) {
            if (!visited[i]) {
                dfs(i);
            }
        }
    }

    public int getCount() {
        return count;
    }

    public boolean isConnected(int v, int w) {
        return id[v] == id[w];
    }
}
