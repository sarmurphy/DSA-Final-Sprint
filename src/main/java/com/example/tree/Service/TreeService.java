package com.example.tree.Service;

import org.springframework.stereotype.Service;

import com.example.tree.Model.TreeDisplayNode;
import com.example.tree.Model.TreeNode;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

@Service
public class TreeService {
    public TreeNode insert(TreeNode root, int value) {
        if (root == null) return new TreeNode(value);
        if (value < root.value) root.left = insert(root.left, value);
        else root.right = insert(root.right, value);
        return root;
    }

    public TreeNode buildNewTree(int[] numbers) {
        TreeNode root = null;
        for (int num : numbers) root = insert(root, num);
        return root;
    }

    public String toJson(TreeNode root) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        mapper.enable(SerializationFeature.INDENT_OUTPUT);
        return mapper.writeValueAsString(root);
    }

    public TreeDisplayNode toDisplayTree(TreeNode root) {
        if (root == null) return null;
        TreeDisplayNode node = new TreeDisplayNode();
        node.setValue(root.value);
        node.setLeft(toDisplayTree(root.left));
        node.setRight(toDisplayTree(root.right));
        
        return node;
    }
}
