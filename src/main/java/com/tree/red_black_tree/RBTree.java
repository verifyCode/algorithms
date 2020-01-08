package com.tree.red_black_tree;

/**
 * @author xjn
 * @since 2020-01-06
 */
public class RBTree<E extends Comparable<E>> {

    private static final boolean RED = true;
    private static final boolean BLACK = false;

    private class Node {
        public E e;
        public Node left;
        public Node right;
        public boolean color;

        public Node(E e) {
            this.e = e;
            this.right = null;
            this.left = null;
            this.color = RED;
        }

        public Node(Node node) {
            this.e = node.e;
            this.left = node.left;
            this.right = node.right;
            this.color = node.color;
        }
    }

    private Node root;
    private int size;


    public RBTree() {
        root = null;
        size = 0;
    }

    private boolean isRed(Node node) {
        if (node == null) {
            //为空,则为黑色节点
            return BLACK;
        }
        return node.color;
    }

    public void add(E e) {
        root = addRecursive(root, e);
        root.color = BLACK;//最终根节点为黑色节点
    }

    public Node addRecursive(Node node, E e) {
        if (node == null) {
            node = new Node(e);
            size++;
            return node;
        }
        if (e.compareTo(node.e) < 0) {
            //为了让整棵二叉树发生改变,在node.left中插入元素e的结果有可能是变化的
            //所以让node.left接住这个变化
            node.left = addRecursive(node.left, e);
        } else if (e.compareTo(node.e) > 0) {
            node.right = addRecursive(node.right, e);
        }
        return node;
    }

    //   node                     x
    //  /   \     左旋转         /  \
    // T1   x   --------->   node   T3
    //     / \              /   \
    //    T2 T3            T1   T2
    private Node leftRotate(Node node) {
        Node x = node.right;

        //左旋转
        node.right = x.left;
        x.left = node;
        x.color = node.color;
        node.color = RED;
        return x;
    }

    //     node                   x
    //    /   \     右旋转       /  \
    //   x    T2   ------->   y   node
    //  / \                       /  \
    // y  T1                     T1  T2
    private Node rightRotate(Node node) {
        Node x = node.left;
        //右旋转
        node.left = x.right;
        x.right = node;

        x.color = node.color;
        node.color = RED;
        return x;
    }

    private void flipColors(Node node) {
        node.color = RED;
        node.left.color = BLACK;
        node.right.color = BLACK;
    }


}
