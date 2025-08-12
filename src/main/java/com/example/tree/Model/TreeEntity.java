package com.example.tree.Model;

import jakarta.persistence.*;

@Entity
public class TreeEntity {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String inputNums;

    @Lob
    private String treeStructure;

    public Long getId() {
        return id;
    }

    public String getInputNums() {
        return inputNums;
    }

    public void setInputNums(String inputNums) {
        this.inputNums = inputNums;
    }

    public String getTreeStructure() {
        return treeStructure;
    }

    public void setTreeStructure(String treeStructure) {
        this.treeStructure = treeStructure;
    }
}
