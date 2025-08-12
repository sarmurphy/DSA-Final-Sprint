package com.example.tree.Controller;

import java.util.Arrays;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import com.example.tree.Model.TreeEntity;
import com.example.tree.Model.TreeNode;
import com.example.tree.Repository.TreeRepository;
import com.example.tree.Service.TreeService;

@Controller
public class TreeController {
    
    private final TreeService treeService;
    private final TreeRepository treeRepository;

    public TreeController(TreeService treeService, TreeRepository treeRepository) {
        this.treeService = treeService;
        this.treeRepository = treeRepository;
    }

    @GetMapping("/enter-numbers")
    public String enterNumbersPage() {
        return "enter-numbers";
    }

    @PostMapping("/process-numbers")
    public String processNumbers(@RequestParam("numbers") String numbers, Model model) throws Exception {
        int[] nums = Arrays.stream(numbers.split(","))
                .map(String::trim)
                .mapToInt(Integer::parseInt)
                .toArray();

        TreeNode tree = treeService.buildNewTree(nums);
        String json = treeService.toJson(tree);

        TreeEntity entity = new TreeEntity();
        entity.setInputNums(numbers);
        entity.setTreeStructure(json);
        treeRepository.save(entity);

        return "redirect:/previous-trees";
    }

    @GetMapping("/previous-trees") 
        public String previousTrees(Model model) {
            List<TreeEntity> trees = treeRepository.findAll();
            model.addAttribute("trees", trees);
            return "previous-trees";
        }
    }
