package com.tree;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Random;
import java.util.Stack;

/**
 * @author xjn
 * @since 2019-12-23
 */
public class BST<E extends Comparable<E>> implements Tree<E> {

    private class Node {
        public E e;
        public Node left;
        public Node right;

        public Node(E e) {
            this.e = e;
            this.right = null;
            this.left = null;
        }

        public Node(Node node) {
            this.e = node.e;
            this.left = node.left;
            this.right = node.right;
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
            //为了让整棵二叉树发生改变,在node.left中插入元素e的结果有可能是变化的
            //所以让node.left接住这个变化
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
        postOrderRecursive(root);
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
            //栈是先进后出,所以下压入右节点,再压入左节点
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
        E ret = maximum();
        root = removeMaximumRecursive(root);
        return ret;
    }

    //删除以node为节点二分搜索树的最大节点
    //返回删除节点后新的二分搜索树的根
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
        E res = minimum();
        root = removeMinimumRecursive(root);
        return res;
    }

    @Override
    public void remove(E e) {
        root = removeRecursive(root, e);
    }

    //删除以node为根的二分搜索树中值为e的节点
    //返回删除节点后新的二分搜索树的根
    private Node removeRecursive(Node node, E e) {
        if (node == null) {
            return null;
        }
        if (e.compareTo(node.e) == 0) {
            //如果右子树为空
            if (node.right == null) {
                size--;
                return node.left;
            }
            //如果左子树为空
            if (node.left == null) {
                size--;
                return node.right;
            }

            //左子树和右子树都不为空
            //找到比待删除节点大的最小的节点,即待删除节点右子树的最小节点
            //用这个节点顶替待删除节点的位置
            Node successor = minimumRecursive(node.right);
            //removeMinimumRecursive返回的是被删除最小值之后的根节点
            successor.right = removeMinimumRecursive(node.right);
            successor.left = node.left;
            node.left = node.right = null;
            return successor;

        } else if (e.compareTo(node.e) < 0) {
            node.left = removeRecursive(node.left, e);
        } else {
            node.right = removeRecursive(node.right, e);
        }
        return node;
    }

    //删除以node为节点二分搜索树的最小值
    //返回删除节点后的新的二分搜索树的根
    private Node removeMinimumRecursive(Node node) {
        if (node.left == null) {
            size--;
            return node.right;
        }
        node.left = removeMinimumRecursive(node.left);
        return node;
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

    public static void add(BST bst, int[] array) {
        for (int i = 0; i < array.length; i++) {
            bst.add(array[i]);
        }
    }

    public static void main(String[] args) {
        BST bst = new BST();
//        int[] array = new int[]{8, 7, 12, 12, 0, 5, 1, 11, 2, 8, 11, 7, 11, 12, 12, 8, 12, 8, 10, 7, 7, 12, 7, 8, 3, 7, 3, 13, 8, 14, 13, 14, 13, 12, 8, 12, 10, 7, 12, 9, 2, 7, 0, 3, 14, 7, 10, 4, 10, 10};
        for (int i = 0; i < 10; i++) {
            Random random = new Random();
            int randInt = random.nextInt(10);
            bst.add(Math.abs(randInt));
        }
//        BST.add(bst, array);
        bst.bfs();
        System.out.println();
        System.out.println("bst.depth:" + bst.getTreeDepth());
        System.out.println(bst);
        System.out.println("bst.maximum:" + bst.maximum());
        System.out.println("bst.minimum:" + bst.minimum());
//        System.out.println("bst.bfs:");
//        bst.bfs();
//        System.out.println("\nbst.dfs:");
//        bst.dfs();
        bst.inOrder();
//        System.out.println("bst.removeMaximum:" + bst.removeMaximum());
//        System.out.println(bst);
//        System.out.println("bst.removeMinimum:" + bst.removeMinimum());
//        System.out.println(bst);
//        System.out.println("bst.remove:8");
//        bst.remove(8);
//        System.out.println(bst);
    }
}
