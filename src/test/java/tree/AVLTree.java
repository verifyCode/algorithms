package tree;

import com.tree.Tree;


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
        node.height =
    }

    @Override
    public boolean contains(E e) {
        return false;
    }

    @Override
    public void preOrder() {

    }

    @Override
    public void inOrder() {

    }

    @Override
    public void postOrder() {

    }

    @Override
    public void bfs() {

    }

    @Override
    public void dfs() {

    }

    @Override
    public int getTreeDepth() {
        return 0;
    }

    @Override
    public E maximum() {
        return null;
    }

    @Override
    public E minimum() {
        return null;
    }

    @Override
    public E removeMaximum() {
        return null;
    }

    @Override
    public E removeMinimum() {
        return null;
    }

    @Override
    public void remove(E e) {

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
