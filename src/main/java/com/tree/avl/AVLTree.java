package com.tree.avl;

import com.tree.Tree;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author xjn
 * @since 2019-12-23
 */
public class AVLTree<E extends Comparable<E>> implements Tree<E> {

    private class Node {
        public E e;
        public Node left;
        public Node right;
        //当前节点的高度值
        public int height;

        public Node(E e) {
            this.e = e;
            this.right = null;
            this.left = null;
            this.height = 1;
        }

        public Node(Node node) {
            this.e = node.e;
            this.left = node.left;
            this.right = node.right;
            this.height = node.height;
        }

        @Override
        public String toString() {
            return e.toString();
        }
    }

    private Node root;
    private int size;

    public AVLTree() {
        this.root = null;
        this.size = 0;
    }

    @Override
    public boolean isEmpty() {
        return this.size == 0;
    }

    private int getHeight(Node node) {
        if (node == null) {
            return 0;
        }
        return node.height;
    }

    //获得节点node的平衡因子
    private int getBalanceFactor(Node node) {
        if (node == null) {
            return 0;
        }
        return getHeight(node.left) - getHeight(node.right);
    }

    //判断是否是二分搜索树
    public boolean isBST() {
//        return isBST(root);
        List<E> arrayList = new ArrayList<>();
        isBST(root, arrayList);
        for (int i = 1; i < arrayList.size(); i++) {
            if (arrayList.get(i - 1).compareTo(arrayList.get(i)) > 0) {
                return false;
            }
        }
        return true;
    }

    private boolean isBST(Node node) {
        Stack<Node> stack = new Stack<>();
        Node pNode = node;
        E pre = minimum();
        while (!stack.isEmpty() || pNode != null) {
            if (pNode != null) {
                stack.push(pNode);
                pNode = pNode.left;
            } else {
                pNode = stack.pop();
                if (pre.compareTo(pNode.e) > 0) {
                    return false;
                }
                pNode = pNode.right;
            }
        }
        return true;
    }

    private void isBST(Node node, List<E> arrayList) {
        if (node == null) {
            return;
        }
        isBST(node.left, arrayList);
        arrayList.add(node.e);
        isBST(node.right, arrayList);
    }

    public boolean isBalanced() {
        return isBalanced(root);
    }

    //判断以node为根的二叉树是否是一课平衡二叉树
    private boolean isBalanced(Node node) {
        if (node == null) {
            return true;
        }
        int balanceFactor = getBalanceFactor(node);
        if (Math.abs(balanceFactor) > 1) {
            return false;
        }
        return isBalanced(node.left) && isBalanced(node.right);
    }

    @Override
    public int getSize() {
        return this.size;
    }

    @Override
    public void add(E e) {
        root = addRecursive(root, e);
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
        //更新height
        node.height = 1 + Math.max(getHeight(node.left), getHeight(node.right));

        //计算平衡因子
        int balance = getBalanceFactor(node);

        //此时不是平衡二叉树
//        if (Math.abs(balance) > 1) {
//            System.out.println("非平衡二叉树,平衡因子:  " + balance + " 节点值:" + node.e);
//        }
        //维护平衡-右旋转 LL
        if (balance > 1 && getBalanceFactor(node.left) >= 0) {
            return rotateRight(node);
        }

        //维护平衡-左旋转 RR
        if (balance < -1 && getBalanceFactor(node.right) <= 0) {
            return rotateLeft(node);
        }

        //LR
        if (balance > 1 && getBalanceFactor(node.left) < 0) {
            node.left = rotateLeft(node.left);
            return rotateRight(node);
        }
        //RL
        if (balance < -1 && getBalanceFactor(node.right) > 0) {
            node.right = rotateRight(node.right);
            return rotateLeft(node);
        }
        return node;
    }

    // 对节点y进行向右旋转操作，返回旋转后新的根节点x
    //        y                              x
    //       / \                           /   \
    //      x   T4     向右旋转 (y)        z     y
    //     / \       - - - - - - - ->    / \   / \
    //    z   T3                       T1  T2 T3 T4
    //   / \
    // T1   T2
    private Node rotateRight(Node y) {
        Node x = y.left;
        Node t3 = x.right;

        x.right = y;
        y.left = t3;

        y.height = Math.max(getHeight(y.left), getHeight(y.right)) + 1;
        x.height = Math.max(getHeight(x.left), getHeight(x.right)) + 1;
        return x;
    }

    // 对节点y进行向左旋转操作，返回旋转后新的根节点x
    //    y                             x
    //  /  \                          /   \
    // T1   x      向左旋转 (y)       y     z
    //     / \   - - - - - - - ->   / \   / \
    //   T2  z                     T1 T2 T3 T4
    //      / \
    //     T3 T4
    private Node rotateLeft(Node y) {
        Node x = y.right;
        Node t2 = x.left;

        x.left = y;
        y.right = t2;

        y.height = Math.max(getHeight(y.left), getHeight(y.left)) + 1;
        x.height = Math.max(getHeight(x.left), getHeight(x.right)) + 1;
        return x;
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
        System.out.println("    dfs:");
        dfs();
        System.out.println("\n  recursive:");
        preOrderRecursive(root);
    }

    private void preOrderRecursive(Node node) {
        if (node == null) {
            return;
        }
        System.out.print(node + " ");
        preOrderRecursive(node.left);
        preOrderRecursive(node.right);
    }

    private void inOrderLoop(Node node) {
        Stack<Node> stack = new Stack<>();
        Node pNode = node;
        while (pNode != null || !stack.isEmpty()) {
            if (pNode != null) {
                stack.push(pNode);
                pNode = pNode.left;
            } else {
                pNode = stack.pop();
                System.out.print(pNode.e + " ");
                pNode = pNode.right;
            }
        }
    }

    @Override
    public void inOrder() {
        System.out.println("    loop:");
        inOrderLoop(root);
        System.out.println("\n    recursive:");
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
        System.out.print(node + " ");
    }

    @Override
    public void bfs() {
        Queue<Node> queue = new ArrayDeque<>();
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
        if (node == null || node.right == null) {
            return node;
        }
        return maximumRecursive(node.right);
    }

    @Override
    public E minimum() {
        return minimumRecursive(root).e;
    }

    private Node minimumRecursive(Node node) {
        if (node == null || node.left == null) {
            return node;
        }
        return minimumRecursive(node.left);
    }

    @Override
    public E removeMaximum() {
        E e = maximum();
        root = removeMaximumRecursive(root);
        return e;
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
        E e = minimum();
        root = removeMinimumRecursive(root);
        return e;
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
        Node retNode;
        if (e.compareTo(node.e) == 0) {
            //左子树为空
            if (node.left == null) {
                Node rightNode = node.right;
                node.right = null;
                //返回右子树
                size--;
                retNode = rightNode;
            } else if (node.right == null) {
                Node leftNode = node.left;
                node.left = null;
                size--;
                retNode = leftNode;
            } else {
                //左右子树均不为空
                Node successor = minimumRecursive(node.right);
                successor.right = removeMinimumRecursive(node.right);
                successor.left = node.left;
                node.left = node.right = null;
                retNode = successor;
            }
        } else if (e.compareTo(node.e) < 0) {
            node.left = removeRecursive(node.left, e);
            //
            retNode = node;
        } else {
            node.right = removeRecursive(node.right, e);
            retNode = node;
            //
        }
        if(retNode == null){
            return null;
        }

        //更新height
        retNode.height = 1 + Math.max(getHeight(retNode.left), getHeight(retNode.right));

        //计算平衡因子
        int balance = getBalanceFactor(retNode);

        //维护平衡-右旋转 LL
        if (balance > 1 && getBalanceFactor(retNode.left) >= 0) {
            return rotateRight(retNode);
        }

        //维护平衡-左旋转 RR
        if (balance < -1 && getBalanceFactor(retNode.right) <= 0) {
            return rotateLeft(retNode);
        }

        //LR
        if (balance > 1 && getBalanceFactor(retNode.left) < 0) {
            retNode.left = rotateLeft(retNode.left);
            return rotateRight(retNode);
        }
        //RL
        if (balance < -1 && getBalanceFactor(retNode.right) > 0) {
            retNode.right = rotateRight(retNode.right);
            return rotateLeft(retNode);
        }

        return retNode;
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
        int left = getTreeDepthRecursive(node.left) + 1;
        int right = getTreeDepthRecursive(node.right) + 1;
        return Math.max(left, right);
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

    public static void add(AVLTree AVLTree, int[] array) {
        for (int i = 0; i < array.length; i++) {
            AVLTree.add(array[i]);
        }
    }

    public static void main(String[] args) {
        AVLTree avlTree = new AVLTree();
        int[] array = new int[]{10, 9, 12, 12, 7, 2, 4, 5, 1};
        for (int i = 0; i < 40; i++) {
            Random random = new Random();
            int randInt = random.nextInt(100);
            avlTree.add(Math.abs(randInt));
        }
        avlTree.add(1);
        avlTree.add(2);
        avlTree.add(3);
        avlTree.add(4);
//        AVLTree.add(avlTree, array);
        System.out.println();
        System.out.println(avlTree);
        avlTree.remove(1);
        avlTree.remove(2);
        avlTree.remove(3);
        System.out.println(avlTree);
//        System.out.println("avlTree.maximum : " + avlTree.maximum());
//        System.out.println("avlTree.minimum : " + avlTree.minimum());
//        avlTree.removeMaximum();
//        System.out.println("avlTree.removeMaximum()");
//        System.out.println(avlTree);
//        System.out.println("avlTree.removeMinimum()");
//        avlTree.removeMinimum();
//        System.out.println(avlTree);
//        System.out.println("先序遍历:");
//        avlTree.preOrder();
//        System.out.println("\n中序遍历:");
//        avlTree.inOrder();
//        System.out.println("\n后序遍历:");
//        avlTree.postOrder();
//        System.out.println();
        System.out.println("avlTree.isBST()" + avlTree.isBST());
        System.out.println("avlTree.isBalanced()" + avlTree.isBalanced());
//        System.out.println();
//        System.out.println(avlTree);
    }
}

