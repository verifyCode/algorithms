package com.tree.avl;

/**
 * @author xjn
 * @since 2020-01-06
 */
public class AVLTree2<E extends Comparable<E>> {

    private class Node {
        public E e;
        public Node left;
        public Node right;
        public int height;

        public Node(E e) {
            this.e = e;
            this.left = null;
            this.right = null;
            this.height = 0;
        }

        public Node(Node node) {
            this.e = node.e;
            this.left = node.left;
            this.right = node.left;
            this.height = node.height;
        }

        @Override
        public String toString() {
            return e.toString() + " ";
        }
    }

    private Node root;
    private int size;

    public AVLTree2() {
        this.root = null;
        this.size = 0;
    }

    public boolean isEmpty() {
        return this.size == 0;
    }

    public int getSize() {
        return this.size;
    }

    private int getHeight(Node node) {
        if (node == null) {
            return 0;
        }
        return node.height;
    }

    private int getBalanceFactor(Node node) {
        if (node == null) {
            return 0;
        }
        return getHeight(node.left) - getHeight(node.right);
    }

    private boolean isBalanced() {
        return isBalanced(root);
    }

    private boolean isBalanced(Node node) {
        if (node == null) {
            return true;
        }
        int balanceFactor = getBalanceFactor(node);
        if (Math.abs(balanceFactor) >= 1) {
            return false;
        }
        return isBalanced(node.left) && isBalanced(node.right);
    }

    public void add(E e) {

    }

    private Node addRecursive(Node node, E e) {
        if (node == null) {
            size++;
            return new Node(e);
        }
        if (e.compareTo(node.e) < 0) {
            node.left = addRecursive(node.left, e);
        } else if (e.compareTo(node.e) > 0) {
            node.right = addRecursive(node.right, e);
        }
        node.height = Math.max(getHeight(node.left), getHeight(node.right)) + 1;

        //LL
        if (getBalanceFactor(node) > 1 && getBalanceFactor(node.left) >= 0) {
            return rotateRight(node);
        }

        //RR
        if (getBalanceFactor(node) < -1 && getBalanceFactor(node.right) <= 0) {
            return rotateLeft(node);
        }

        //LR
        if(getBalanceFactor(node) > 1 && getBalanceFactor(node.left) < 0){
            node.left = rotateLeft(node.left);
        }

        //RL
        if(getBalanceFactor(node) < -1 && getBalanceFactor(node.right) > 0){

        }
        return null;
    }

    private Node rotateRight(Node y) {
        Node x = y.left;
        Node t3 = x.right;

        x.right = y;
        y.left = t3;

        y.height = Math.max(getHeight(y.left), getHeight(y.right)) + 1;
        x.height = Math.max(getHeight(x.left), getHeight(x.right)) + 1;
        return x;
    }

    private Node rotateLeft(Node y) {
        Node x = y.right;
        Node t3 = x.left;

        y.right = t3;
        x.left = y;

        y.height = Math.max(getHeight(y.left), getHeight(y.right)) + 1;
        x.height = Math.max(getHeight(x.left), getHeight(x.right)) + 1;
        return x;
    }
}
