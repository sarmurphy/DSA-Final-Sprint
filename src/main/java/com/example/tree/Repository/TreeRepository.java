package com.example.tree.Repository;

import com.example.tree.Model.TreeEntity;

import org.springframework.data.jpa.repository.JpaRepository;

public interface TreeRepository extends JpaRepository<TreeEntity, Long> {
}
