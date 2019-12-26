package com.map;


import java.util.LinkedList;
import java.util.Queue;
import java.util.Random;
import java.util.Stack;

/**
 * @author xjn
 * @since 2019-12-26
 */
public class BST<K extends Comparable, V> implements Tree<K, V> {

    private class Node {
        public K key;
        public V value;
        public Node left;
        public Node right;

        public Node(K key, V value) {
            this.key = key;
            this.value = value;
            this.left = null;
            this.right = null;
        }

        public Node() {
            this.key = null;
            this.value = null;
            this.left = null;
            this.right = null;
        }

        @Override
        public String toString() {
//            return "(" + key + "," + value + ")";
            return key.toString();
        }
    }

    private Node root;
    private int size;


    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public int getSize() {
        return this.getSize();
    }

    @Override
    public void add(K key, V value) {
        root = addRecursive(root, key, value);
    }

    private Node addRecursive(Node node, K key, V value) {
        if (node == null) {
            size++;
            node = new Node(key, value);
            return node;
        }
        if (key.compareTo(node.key) < 0) {
            node.left = addRecursive(node.left, key, value);
        } else if (key.compareTo(node.key) > 0) {
            node.right = addRecursive(node.right, key, value);
        }
        return node;
    }

    @Override
    public boolean contains(K key) {
        return containsRecursive(root, key);
    }

    private boolean containsRecursive(Node node, K key) {
        if (node == null) {
            return false;
        }
        if (key.compareTo(node.key) == 0) {
            return true;
        } else if (key.compareTo(node.key) < 0) {
            return containsRecursive(node.left, key);
        } else {
            return containsRecursive(node.right, key);
        }
    }

    @Override
    public void preOrder() {
        preOrderRecursive(root);
    }

    private void preOrderRecursive(Node node) {
        if (node == null) {
            return;
        }
        System.out.print(node + "   ");
        preOrderRecursive(node.left);
        preOrderRecursive(node.right);
    }


    @Override
    public void inOrder() {
        inOrderRecursive(root);
    }

    private void inOrderRecursive(Node node) {
        if (node == null) {
            return;
        }
        preOrderRecursive(node.left);
        System.out.print(node + "   ");
        preOrderRecursive(node.right);
    }

    @Override
    public void postOrder() {
        postOrderRecursive(root);
    }

    private void postOrderRecursive(Node node) {
        if (node == null) {
            return;
        }
        preOrderRecursive(node.left);
        preOrderRecursive(node.right);
        System.out.print(node + "   ");
    }

    @Override
    public void bfs() {
        Queue<Node> queue = new LinkedList<>();
        queue.add(root);
        while (!queue.isEmpty()) {
            Node nodeFromQueue = queue.remove();
            System.out.print(nodeFromQueue + " ");
            if (nodeFromQueue.left != null) {
                queue.add(nodeFromQueue.left);
            }
            if (nodeFromQueue.right != null) {
                queue.add(nodeFromQueue.right);
            }
        }
    }

    @Override
    public void dfs() {
        Stack<Node> stack = new Stack<>();
        stack.push(root);
        while (!stack.isEmpty()) {
            Node nodeFromStack = stack.pop();
            System.out.print(nodeFromStack + " ");
            if (nodeFromStack.right != null) {
                stack.push(nodeFromStack.right);
            }
            if (nodeFromStack.left != null) {
                stack.push(nodeFromStack.left);
            }
        }
    }

    @Override
    public int getTreeDepth() {
        return getTreeDepth(root);
    }

    private int getTreeDepth(Node node) {
        if (node == null) {
            return 0;
        }
        int left = getTreeDepth(node.left) + 1;
        int right = getTreeDepth(node.right) + 1;
        return Math.max(left, right);
    }

    @Override
    public V maximum() {
        return maximumRecursive(root).value;
    }

    private Node maximumRecursive(Node node) {
        if (node.right == null) {
            return node;
        }
        return maximumRecursive(node.right);
    }

    @Override
    public V minimum() {
        return minimumRecursive(root).value;
    }

    private Node minimumRecursive(Node node) {
        if (node.left == null) {
            return node;
        }
        return minimumRecursive(node.left);
    }

    @Override
    public V removeMaximum() {
        V v = maximum();
        root = removeMaximumRecursive(root);
        return v;
    }

    private Node removeMaximumRecursive(Node node) {
        if (node.right == null) {
            size--;
            return node.left;
        }
        node.right = removeMaximumRecursive(node.right);
        return node;
    }


    @Override
    public V removeMinimum() {
        return removeMinimumRecursive(root).value;
    }

    private Node removeMinimumRecursive(Node node) {
        if (node.left == null) {
            size--;
            return node.right;
        }
        node.left = removeMinimumRecursive(node.left);
        return node;
    }

    @Override
    public void remove(K k) {
        root = removeRecursive(root, k);
    }

    private Node removeRecursive(Node node, K key) {
        if (node == null) {
            return null;
        }
        if (key.compareTo(node.key) == 0) {
            //左子树为空
            if (node.left == null) {
                size--;
                return node.right;
            }

            //右子树为空
            if (node.right == null) {
                size--;
                return node.left;
            }
            //左右子树均不为空
            Node successor = minimumRecursive(node.right);
            successor.right = removeMinimumRecursive(node.right);
            successor.left = node.left;
            node.left = node.right = null;
            return successor;
        } else if (key.compareTo(node.key) < 0) {
            node.left = removeRecursive(node.left, key);
        } else {
            node.right = removeRecursive(node.right, key);
        }
        return node;
    }


    @Override
    public String toString() {
        return show(root);
    }

    private void writeArray(Node node, int rowIndex, int columnIndex, String[][] res, int treeDepth) {
        //输入的树不为空
        if (node == null) {
            return;
        }
        //当前节点保存到二维数组中
        res[rowIndex][columnIndex] = String.valueOf(node.key);
        //当前节点位于树的第几层
        int currLevel = (rowIndex + 1) / 2;
        //如果到了最后一行,则返回
        if (currLevel == treeDepth) {
            return;
        }
        // 计算当前行到下一行，每个元素之间的间隔（下一行的列索引与当前元素的列索引之间的间隔）
        int gap = treeDepth - currLevel - 1;
        // 对左儿子进行判断，若有左儿子，则记录相应的"/"与左儿子的值
        if (node.left != null) {
            res[rowIndex + 1][columnIndex - gap] = "/";
            writeArray(node.left, rowIndex + 2, columnIndex - gap * 2, res, treeDepth);
        }
        // 对右儿子进行判断，若有右儿子，则记录相应的"\"与右儿子的值
        if (node.right != null) {
            res[rowIndex + 1][columnIndex + gap] = "\\";
            writeArray(node.right, rowIndex + 2, columnIndex + gap * 2, res, treeDepth);
        }
    }

    private String show(Node node) {
        StringBuilder builder = new StringBuilder();
        if (root == null) {
            return "EMPTY!";
        }
        int treeDepth = getTreeDepth();
        // 最后一行的宽度为2的（n - 1）次方乘3，再加1
        // 作为整个二维数组的宽度
        int arrayHeight = treeDepth * 2 - 1;
        int arrayWidth = (2 << (treeDepth - 2)) * 3 + 1;
        // 用一个字符串数组来存储每个位置应显示的元素
        String[][] res = new String[arrayHeight][arrayWidth];
        // 对数组进行初始化，默认为一个空格
        for (int i = 0; i < arrayHeight; i++) {
            for (int j = 0; j < arrayWidth; j++) {
                res[i][j] = " ";
            }
        }
        // 从根节点开始，递归处理整个树
        // res[0][(arrayWidth + 1)/ 2] = (char)(root.val + '0');
        writeArray(node, 0, arrayWidth / 2, res, treeDepth);
        // 此时，已经将所有需要显示的元素储存到了二维数组中，将其拼接并打印即可
        for (String[] line : res) {
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < line.length; i++) {
                sb.append(line[i]);
                if (line[i].length() > 1 && i <= line.length - 1) {
                    i += line[i].length() > 4 ? 2 : line[i].length() - 1;
                }
            }
            builder.append(sb.toString() + "\n");
        }
        return builder.toString();
    }

    public static void main(String[] args) {
        BST bst = new BST();
        bst.add(1, 1);
        for (int i = 0; i < 20; i++) {
            Random random = new Random();
            int randInt = random.nextInt(25);
            bst.add(Math.abs(randInt), Math.abs(randInt));
        }
        System.out.println(bst);
        System.out.println("bst.maximum:" + bst.maximum());
        System.out.println("bst.minimum:" + bst.minimum());
        bst.remove(1);
        System.out.println(bst);
    }

}
