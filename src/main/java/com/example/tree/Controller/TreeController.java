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

    @GetMapping("/")
    public String redirectToForm() {
        return "redirect:/enter-numbers";
    }

    @PostMapping("/process-numbers")
    public String processNumbers(@RequestParam("numbers") String numbers, Model model) {
        if (numbers == null || numbers.trim().isEmpty()) {
            model.addAttribute("Error", "Please enter a number.");
            return "enter-numbers";
        }

        try {
            int[] nums = Arrays.stream(numbers.split(","))
                         .map(String::trim)
                         .filter(s -> !s.isEmpty())
                         .mapToInt(Integer::parseInt)
                         .toArray();

            if (nums.length == 0) {
                model.addAttribute("Error", "No numbers found. Please enter comma-separated numbers.");
                return "enter-numbers";
            }

            TreeNode tree = treeService.buildNewTree(nums);
            String json = treeService.toJson(tree);

            TreeEntity entity = new TreeEntity();
            entity.setInputNums(numbers);
            entity.setTreeStructure(json);
            treeRepository.save(entity);

            return "redirect:/previous-trees";
        } catch (NumberFormatException exception) {
            model.addAttribute("Error", "Invalid Entry: Please use comma-seaprated numbers only.");
            return "enter-numbers";
        } catch (Exception exception) {
            model.addAttribute("Error", "Please try again.");
            return "enter-numbers";
        }
    }

    @GetMapping("/previous-trees") 
        public String previousTrees(Model model) {
            List<TreeEntity> trees = treeRepository.findAll();
            model.addAttribute("trees", trees);
            return "previous-trees";
        }
    }
