package com.graph;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

/**
 * @author xjn
 * @since 2019-12-14
 */
public class ReadGraph {
    private String fileName;
    private Graph graph;

    public static void initGraphFromFile(String fileName, Graph graph) {
        try {
            int V;
            int E;
            List<String> list = Files.readAllLines(Paths.get(fileName));
            String[] s = list.get(0).split(" ");
            V = Integer.parseInt(s[0]);
            E = Integer.parseInt(s[1]);
            list.stream().skip(1).forEach(e -> {
                int a = Integer.parseInt(e.split(" ")[0]);
                int b = Integer.parseInt(e.split(" ")[1]);
                if (a >= 0 && a < V && b >= 0 && b < V) {
                    graph.addEdge(a, b);
                }
            });

        } catch (Exception e) {
            System.err.println(e);
        }
    }

}
