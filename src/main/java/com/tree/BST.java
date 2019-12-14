package com.tree;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Random;

/**
 * @author xjn
 * @since 2019-12-09
 */
public class BST<Key extends Comparable<Key>, Value> {

    private Node root;
    private int count;

    private class Node {
        private Key key;
        private Value value;
        private Node left, right;

        Node(Key key, Value value) {
            this.key = key;
            this.value = value;
            this.left = null;
            this.right = null;
        }

        Node(Node node) {
            this.key = node.key;
            this.value = node.value;
            this.right = node.right;
            this.left = node.left;
        }
    }

    public void insert(Key key, Value value) {
        root = insertRecursive(root, key, value);
    }

    public BST() {
        root = null;
        count = 0;
    }

    public int getSize() {
        return count;
    }

    public boolean isEmpty() {
        return count == 0;
    }

    // 向以node为跟节点的二叉搜索树中插入节点
    public Node insertRecursive(Node node, Key key, Value value) {
        if (node == null) {
            count++;
            return new Node(key, value);
        }
        if (node.key.compareTo(key) == 0) {
            node.key = key;
            node.value = value;
        } else if (node.key.compareTo(key) > 0) {
            node.left = insertRecursive(node.left, key, value);
        } else {
            node.right = insertRecursive(node.right, key, value);
        }
        return node;
    }

    public Value get(Key key) {
        return search(root, key);
    }

    public Value search(Node node, Key key) {
        if (node == null) {
            return null;
        }
        if (node.key.compareTo(key) == 0) {
            return node.value;
        } else if (node.key.compareTo(key) < 0) {
            return search(node.right, key);
        } else {
            return search(node.right, key);
        }
    }

    public void preOrder() {
        preOrder(root);
    }

    //前序遍历
    private void preOrder(Node node) {
        if (node != null) {
            System.out.print(node.key + " ");
            preOrder(node.left);
            preOrder(node.right);
        }
    }

    //广度优先遍历
    public void levelOrder() {
        Queue<Node> queue = new LinkedList();
        queue.add(root);
        while (!queue.isEmpty()) {
            Node node = queue.remove();
            System.out.print(node.key + " ");
            if (node.left != null) {
                queue.add(node.left);
            }
            if (node.right != null) {
                queue.add(node.right);
            }
        }
    }

    public Key minimum() {
        Node node = minimumRecursive(root);
        if (node != null) {
            return node.key;
        }
        return null;
    }

    //递归获取最小节点
    private Node minimumRecursive(Node node) {
        if (node.left == null) {
            return node;
        }
        return minimumRecursive(node.left);
    }

    public Key minimumLoop() {
        Node temp = root;
        while (temp.left != null) {
            temp = temp.left;
        }
        return temp.key;
    }

    public Key maximum() {
        Node node = maximumRecursive(root);
        if (node != null) {
            return node.key;
        }
        return null;
    }

    public Key maximumLoop() {
        Node temp = root;
        while (temp.right != null) {
            temp = temp.right;
        }
        return temp.key;
    }

    public void removeMaximum() {
        if (root != null) {
            root = removeMaximumRecursive(root);
        }
    }

    public void remove(Key key) {
        root = removeRecursive(root, key);
    }

    private Node removeRecursive(Node node, Key key) {
        if (node == null) {
            return null;
        }
        if (node.key.compareTo(key) > 0) {
            node.left = removeRecursive(node.left, key);
            return node;
        } else if (node.key.compareTo(key) < 0) {
            node.right = removeRecursive(node.right, key);
            return node;
        } else {
            //key = node.key
            //
            if (node.left == null) {
                Node rightNode = node.right;
                count--;
                return rightNode;
            }
            if (node.right == null) {
                Node leftNode = node.left;
                count--;
                return leftNode;
            }

            //node.left != null && node.right != null
            Node successor = new Node(minimumRecursive(node.right));
            count++;
            successor.right = removeMinRecursive(node.right);
            successor.left = node.left;
            node = null;
            return successor;
        }
    }

    private Node removeMaximumRecursive(Node node) {
        if (node.right == null) {
            Node leftNode = node.left;
            count--;
            return leftNode;
        }
        node.right = removeMaximumRecursive(node.right);
        return node;
    }

    public void removeMin() {
        if (root != null) {
            root = removeMinRecursive(root);
        }
    }

    private Node removeMinRecursive(Node node) {
        if (node.left == null) {
            Node right = node.right;
            count--;
            return right;
        }
        node.left = removeMinRecursive(node.left);
        return node;
    }

    private Node maximumRecursive(Node node) {
        if (node.right == null) {
            return node;
        }
        return maximumRecursive(node.right);
    }

    public static void main(String[] args) {
        BST bst = new BST();
        for (int i = 0; i < 10; i++) {
            Random random = new Random();
            int randInt = random.nextInt(10);
            bst.insert(Math.abs(randInt), i);
        }
        bst.insert(100, 100);
        bst.levelOrder();
        System.out.println();
        bst.preOrder();
        System.out.println("\nBST minimumRecursive:" + bst.minimum());
        System.out.println("BST minimumLoop:" + bst.minimumLoop());
        System.out.println("BST maximumRecursive:" + bst.maximum());
        System.out.println("BST maximumLoop:" + bst.maximumLoop());
        bst.removeMin();
        bst.levelOrder();
        System.out.println("\nBST minimumRecursive:" + bst.minimum());
        bst.remove(100);
        System.out.println("after bst.remove key " );
        bst.levelOrder();

    }
}
