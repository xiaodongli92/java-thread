package com.xiaodong.java.tree;

/**
 * Created by xiaodong on 2017/5/25.
 *
 *              4
 *          10      5
 *       3      1 11    8
 */
public class VisitBinaryTree {

    public static void main(String[] args) {
        BinaryTree root = getInitBinaryTree();
        before(root);
        System.out.println();
        mid(root);
        System.out.println();
        after(root);
        System.out.println();
        System.out.println(getDepth(root));
        System.out.println(getNodeNum(root));
    }

    private static int getNodeNum(BinaryTree root) {
        if (root == null) {
            return 0;
        }
        return getNodeNum(root.getLeft()) + getNodeNum(root.getRight()) + 1;
    }

    private static int getDepth(BinaryTree root) {
        if (root == null) {
            return 0;
        }
        int leftDepth = getDepth(root.getLeft());
        int rightDepth = getDepth(root.getRight());
        return leftDepth > rightDepth ? (leftDepth + 1) : (rightDepth + 1);
    }

    private static void after(BinaryTree root) {
        if (null == root) {
            return;
        }
        after(root.getLeft());
        after(root.getRight());
        visit(root);
    }

    private static void mid(BinaryTree root) {
        if (null == root) {
            return;
        }
        mid(root.getLeft());
        visit(root);
        mid(root.getRight());
    }

    private static void before(BinaryTree root) {
        if (null == root) {
            return;
        }
        visit(root);
        before(root.getLeft());
        before(root.getRight());
    }

    private static void visit(BinaryTree tree) {
        System.out.print(tree.getValue() + "\t");
    }

    private static BinaryTree getInitBinaryTree() {
        BinaryTree leftLeftTree = new BinaryTree();
        leftLeftTree.setValue(3);
        BinaryTree leftRightTree = new BinaryTree();
        leftRightTree.setValue(1);
        BinaryTree leftTree = new BinaryTree();
        leftTree.setValue(10);
        leftTree.setLeft(leftLeftTree);
        leftTree.setRight(leftRightTree);

        BinaryTree rightLeftTree = new BinaryTree();
        rightLeftTree.setValue(11);
        BinaryTree rightRightTree = new BinaryTree();
        rightRightTree.setValue(8);
        BinaryTree rightTree = new BinaryTree();
        rightTree.setValue(5);
        rightTree.setLeft(rightLeftTree);
        rightTree.setRight(rightRightTree);

        BinaryTree root = new BinaryTree();
        root.setValue(4);
        root.setLeft(leftTree);
        root.setRight(rightTree);
        return root;
    }
}
