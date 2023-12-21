package com.group20.backend.controller;

import com.group20.backend.model.Category;
import com.group20.backend.service.CategoryService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.concurrent.ExecutionException;

@RestController
@RequestMapping("api/category")
public class CategoryController {

    private final CategoryService categoryService;

    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @PostMapping
    public boolean createCategory(@RequestBody Category category) {
        return categoryService.create(category);
    }

    @GetMapping
    public Category getCategoryById(@RequestParam String id) throws ExecutionException, InterruptedException {
        return categoryService.findById(id).orElse(null);
    }

    @GetMapping(value = "all")
    public List<Category> getAll() throws ExecutionException, InterruptedException {
        return categoryService.getAll();
    }
}
