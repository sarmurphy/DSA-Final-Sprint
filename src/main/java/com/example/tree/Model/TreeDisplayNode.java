package com.example.tree.Model;

public class TreeDisplayNode {
    private int value;
    private TreeDisplayNode left;
    private TreeDisplayNode right;

    public TreeDisplayNode() {}

    public TreeDisplayNode(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public TreeDisplayNode getLeft() {
        return left;
    }

    public void setLeft(TreeDisplayNode left) {
        this.left = left;
    }

    public TreeDisplayNode getRight() {
        return right;
    }

    public void setRight(TreeDisplayNode right) {
        this.right = right;
    }
}
