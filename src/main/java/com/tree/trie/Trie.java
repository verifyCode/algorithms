package com.tree.trie;

import java.util.TreeMap;

/**
 * @author xjn
 * @since 2020-01-02
 */
public class Trie {
    private class Node {
        public boolean isWord;
        public TreeMap<Character, Node> next;

        public Node(boolean isWord) {
            this.isWord = isWord;
            next = new TreeMap<>();
        }

        public Node() {
            this(false);
        }
    }

    private Node root;
    //存储单词的数量
    private int size;

    public Trie() {
        root = new Node();
        size = 0;
    }

    public int getSize() {
        return this.size;
    }

    // 向Trie中添加一个新的单词word
    public void add(String word) {
        Node cur = root;
        for (int i = 0; i < word.length(); i++) {
            Character curChar = word.charAt(i);
            if (cur.next.get(curChar) == null) {
                cur.next.put(curChar, new Node());
            }
            cur = cur.next.get(curChar);
        }
        if (!cur.isWord) {
            cur.isWord = true;
            size++;
        }
    }


    public boolean contains(String word) {
        Node cur = root;
        for (int i = 0; i < word.length(); i++) {
            Character curChar = word.charAt(i);
            if (cur.next.get(curChar) == null) {
                return false;
            }
            cur = cur.next.get(curChar);
        }
        return cur.isWord;
    }

    public boolean isPrefix(String prefix) {
        Node cur = root;
        for (int i = 0; i < prefix.length(); i++) {
            Character curChar = prefix.charAt(i);
            if (cur.next.get(curChar) == null) {
                return false;
            }
            cur = cur.next.get(curChar);
        }
        return true;
    }

    public static void main(String[] args) {
        Trie trie = new Trie();
        trie.add("Hello");
        trie.add("bye");
        System.out.println(trie.contains("Hello"));
        System.out.println(trie.isPrefix("He"));
    }
}
