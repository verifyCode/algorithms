package com.graph;

/**
 * @author xjn
 * @since 2019-12-14
 */
public class Main {
    public static void main(String[] args) {
        // TestG1.txt
        String filename1 = "/Users/xujinniu/code/algorithms/src/main/java/com/graph/testG1.txt";
        SparseGraph g1 = new SparseGraph(13, false);
        ReadGraph.initGraphFromFile(filename1, g1);
        Components component1 = new Components(g1);
        System.out.println("TestG1.txt, Component Count: " + component1.getCount());
        System.out.println();

        // TestG2.txt
        String filename2 = "/Users/xujinniu/code/algorithms/src/main/java/com/graph/testG2.txt";
        SparseGraph g2 = new SparseGraph(6, false);
        ReadGraph.initGraphFromFile(filename2, g2);
        Components component2 = new Components(g2);
        System.out.println("TestG2.txt, Component Count: " + component2.getCount());
    }
}
