package tree;

import com.tree.Tree;

import java.util.LinkedList;
import java.util.Optional;
import java.util.Queue;
import java.util.Stack;


/**
 * @author xjn
 * @since 2020-05-07
 */
public class AVLTree<E extends Comparable<E>> implements Tree<E> {
    private class Node {
        public E e;
        public Node left;
        public Node right;
        public int height;

        public Node(E e) {
            this.e = e;
            this.left = this.right = null;
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

    public boolean isAVL() {
        return isAVL(this.root);
    }

    private boolean isAVL(Node node) {
        if (node == null) {
            return true;
        }
        int leftHeight = getHeight(node.left);
        int rightHeight = getHeight(node.right);
        if (Math.abs(leftHeight - rightHeight) > 1) {
            return false;
        }
        return isAVL(node.left) && isAVL(node.right);
    }


    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public int getSize() {
        return this.size;
    }

    @Override
    public void add(E e) {

    }

    private Node addRecursive(Node node, E e) {
        if (node == null) {
            node = new Node(e);
            size++;
            return node;
        }
        if (node.e.compareTo(e) < 0) {
            node.left = addRecursive(node.left, e);
        } else if (node.e.compareTo(e) > 0) {
            node.right = addRecursive(node.right, e);
        }
        //更新height
        node.height = 1 + Math.max(getHeight(node.left), getHeight(node.right));

        //平衡因子
        int balanceFactory = getBalanceFactor(node);

        //RR
        if (balanceFactory > 1 && getBalanceFactor(node.left) >= 0) {
            return rotateRight(node);
        }

        //LL
        if (balanceFactory < -1 && getBalanceFactor(node.right) <= 0) {
            return rotateLeft(node);
        }

        //LR
        if (balanceFactory > 1 && getBalanceFactor(node.left) < 0) {
            node.left = rotateLeft(node.left);
            return rotateRight(node);
        }

        //RL
        if (balanceFactory < -1 && getBalanceFactor(node.right) > 0) {
            node.right = rotateRight(node.right);
            return rotateLeft(node);
        }
        return node;
    }


    private Node rotateRight(Node y) {
        Node x = y.left;
        Node t3 = x.right;

        x.right = y;
        y.left = t3;
        y.height = Math.max(getHeight(y.left), getHeight(y.right)) + 1;
        x.height = Math.max(getHeight(x.right), getHeight(x.left)) + 1;
        return x;
    }

    private Node rotateLeft(Node y) {
        Node x = y.right;
        Node t3 = x.left;

        x.left = y;
        y.right = t3;

        y.height = Math.max(getHeight(y.left), getHeight(y.right)) + 1;
        x.height = Math.max(getHeight(x.left), getHeight(x.right)) + 1;
        return x;
    }

    @Override
    public boolean contains(E e) {
        return false;
    }

    private boolean containsRecursive(Node node, E e) {
        if (node == null) {
            return false;
        }
        if (node.e.compareTo(e) == 0) {
            return true;
        } else if (node.e.compareTo(e) > 0) {
            return containsRecursive(node.left, e);
        } else {
            return containsRecursive(node.right, e);
        }
    }

    @Override
    public void preOrder() {
        preOrder(root);
    }

    private void preOrder(Node node) {
        if (node == null) {
            return;
        }
        System.out.print(node);
        preOrder(node.left);
        preOrder(node.right);
    }

    @Override
    public void inOrder() {
        inOrder(root);
    }

    private void inOrder(Node node) {
        if (node == null) {
            return;
        }
        preOrder(node.left);
        System.out.print(node);
        preOrder(node.right);
    }

    @Override
    public void postOrder() {
        postOrder(root);
    }

    private void postOrder(Node node) {
        if (node == null) {
            return;
        }
        postOrder(node.left);
        postOrder(node.right);
        System.out.print(node);
    }

    @Override
    public void bfs() {
        Queue<Node> queue = new LinkedList<>();
        queue.add(root);
        while (!queue.isEmpty()) {
            Node node = queue.remove();
            System.out.print(node + " ");
            if (node.left != null) {
                queue.add(node.left);
            }
            if (node.right != null) {
                queue.add(node.right);
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
    public E maximum() {
        return maximum(root).e;
    }

    private Node maximum(Node node) {
        if (node == null || node.left == null) {
            return node;
        }
        return maximum(node.left);
    }

    @Override
    public E minimum() {
        return minimum(root).e;
    }

    private Node minimum(Node node) {
        if (node == null || node.right == null) {
            return node;
        }
        return minimum(node.right);
    }

    @Override
    public E removeMaximum() {
        E e = maximum();
        root = removeMaximum(root);
        return e;
    }

    private Node removeMaximum(Node node) {
        if (node.left == null) {
            size--;
            return node.right;
        }
        node.left = removeMaximum(node.left);
        return node;
    }

    @Override
    public E removeMinimum() {
        return null;
    }

    private Node removeMinimum(Node node) {
        if (node.right == null) {
            size--;
            return node.left;
        }
        node.right = removeMinimum(node.right);
        return node;
    }

    @Override
    public void remove(E e) {

    }

    private Node remove(Node node, E e) {
        if (node == null) {
            return null;
        }
        Node retNode = null;
        if (node.e.compareTo(e) < 0) {
            node.left = remove(node.left, e);
            retNode = node;
        } else if (node.e.compareTo(e) > 0) {
            node.right = remove(node.right, e);
            retNode = node;
        }
        //找到删除的位置 node

        //左子树为空
        if (node.left == null) {
            Node rightNode = node.right;
            node.right = null;
            size--;
            retNode = rightNode;
        } else if (node.right == null) {
            //右子树为空
            Node leftNode = node.left;
            node.left = null;
            size--;
            retNode = leftNode;
        } else {
            //左右子树不为空
            Node successor = minimum(node.right);
            successor.left = node.left;
            successor.right = removeMinimum(node.right);
            node.left = node.right = null;
            retNode = successor;
        }

        if (retNode == null) {
            return null;
        }
        //更新height
        retNode.height = Math.max(getHeight(retNode.right), getHeight(retNode.left)) + 1;

        //计算平衡因子
        int balanceFactor = getBalanceFactor(retNode);

        //右旋
        if (balanceFactor > 1 && getBalanceFactor(retNode.left) >= 0) {
            return rotateRight(retNode);
        }
        //左旋
        if (balanceFactor < -1 && getBalanceFactor(retNode.right) <= 0) {
            return rotateLeft(retNode);
        }

        //LR
        if (balanceFactor > 1 && getBalanceFactor(retNode.left) < 0) {
            retNode.left = rotateLeft(retNode.left);
            return rotateRight(retNode);
        }
        //RL
        if (balanceFactor < -1 && getBalanceFactor(retNode.right) > 0) {
            retNode.right = rotateRight(retNode.right);
            return rotateLeft(retNode);
        }
        return retNode;
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
}
