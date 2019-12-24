package com.tree;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Random;
import java.util.Stack;

/**
 * @author xjn
 * @since 2019-12-23
 */
public class BST2<E extends Comparable<E>> implements Tree<E> {

    private class Node {
        public E e;
        public Node left;
        public Node right;

        public Node(E e) {
            this.e = e;
            this.right = null;
            this.left = null;
        }
    }

    private Node root;
    private int size;

    @Override
    public boolean isEmpty() {
        return this.size == 0;
    }

    @Override
    public int getSize() {
        return this.size;
    }

    @Override
    public void add(E e) {
        root = addRecursive(this.root, e);
    }

    private Node addRecursive(Node node, E e) {
        if (node == null) {
            node = new Node(e);
            size++;
            return node;
        }
        if (e.compareTo(node.e) < 0) {
            node.left = addRecursive(node.left, e);
        } else if (e.compareTo(node.e) > 0) {
            node.right = addRecursive(node.right, e);
        }
        return node;
    }

    @Override
    public boolean contains(E e) {
        return containsRecursive(this.root, e);
    }

    private boolean containsRecursive(Node node, E e) {
        if (node == null) {
            return false;
        }
        if (e.compareTo(node.e) == 0) {
            return true;
        } else if (e.compareTo(node.e) < 0) {
            return containsRecursive(node.left, e);
        } else {
            return containsRecursive(node.right, e);
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
        System.out.print(node.e + " ");
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
        inOrderRecursive(node.left);
        System.out.print(node.e + " ");
        inOrderRecursive(node.right);
    }

    @Override
    public void postOrder() {

    }

    @Override
    public void bfs() {
        Queue<Node> queue = new LinkedList<>();
        queue.add(root);
        while (!queue.isEmpty()) {
            Node nodeFromQueue = queue.remove();
            System.out.print(nodeFromQueue.e + " ");
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
            System.out.print(nodeFromStack.e + " ");
            if (nodeFromStack.left != null) {
                stack.push(nodeFromStack.left);
            }
            if (nodeFromStack.right != null) {
                stack.push(nodeFromStack.right);
            }
        }
    }

    private void postOrderRecursive(Node node) {
        if (node == null) {
            return;
        }
        postOrderRecursive(node.left);
        postOrderRecursive(node.right);
        System.out.print(node.e + " ");
    }

    @Override
    public int getTreeDepth() {
        return getTreeDepthRecursive(root);
    }

    @Override
    public E maximum() {
        return maximumRecursive(root).e;
    }

    private Node maximumRecursive(Node node) {
        if (node.right == null) {
            return node;
        }
        return maximumRecursive(node.right);
    }

    @Override
    public E minimum() {
        return minimumRecursive(root).e;
    }

    private Node minimumRecursive(Node node) {
        if (node.left == null) {
            return node;
        }
        return minimumRecursive(node.left);
    }

    @Override
    public E removeMaximum() {
        return removeMaximumRecursive(root).e;
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
    public E removeMinimum() {
        return null;
    }

    private int getTreeDepthRecursive(Node node) {
        if (node == null) {
            return 0;
        }
        int leftDepth = getTreeDepthRecursive(node.left) + 1;
        int rightDepth = getTreeDepthRecursive(node.right) + 1;
        return Math.max(leftDepth, rightDepth);
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
        res[rowIndex][columnIndex] = String.valueOf(node.e);
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
        BST2 bst = new BST2();
        int[] array = new int[]{5, 3, 6, 8, 4, 2};
        for (int i = 0; i < 10; i++) {
            Random random = new Random();
            int randInt = random.nextInt(10);
            bst.add(Math.abs(randInt));
        }
        bst.bfs();
        System.out.println();
        System.out.println(bst.getTreeDepth());
        System.out.println(bst);
        System.out.println("bst.maximum:" + bst.maximum());
        System.out.println("bst.minimum:" + bst.minimum());
        System.out.println("bst.removeMaximum:" + bst.removeMaximum());
        System.out.println(bst);
    }
}
