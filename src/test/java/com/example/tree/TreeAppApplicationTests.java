package com.example.tree;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.tree.Model.TreeNode;
import com.example.tree.Service.TreeService;

@SpringBootTest
class TreeAppApplicationTests {

	@Test
	void testBuildTree() {
		TreeService treeService = new TreeService();
		int[] nums = {5, 3, 7};
		TreeNode root = treeService.buildNewTree(nums);
		assertEquals(5, root.value);
		assertEquals(3, root.left.value);
		assertEquals(7, root.right.value);
	}

	@Test
	void testJsonSerialization() throws Exception {
		TreeService treeService = new TreeService();
		TreeNode root = treeService.buildNewTree(new int[]{3, 7});
		String json = treeService.toJson(root);
		assertTrue(json.contains("3"));
		assertTrue(json.contains("7"));
	}

	@Test
	void testEmptyTree() throws Exception {
		TreeService treeService = new TreeService();
		TreeNode root = treeService.buildNewTree(new int[]{});
		assertNull(root);
	}

}
