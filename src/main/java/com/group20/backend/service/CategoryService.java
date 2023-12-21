package com.group20.backend.service;

import com.group20.backend.model.Category;
import com.group20.backend.repository.CategoryRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.ExecutionException;

@Service
public class CategoryService implements BasicCrudService<Category> {

    private CategoryRepository categoryRepository;

    public CategoryService(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @Override
    public boolean create(Category category) {
        return categoryRepository.create(category);
    }

    @Override
    public Optional<Category> findById(String id) throws ExecutionException, InterruptedException {
        return categoryRepository.findById(id);
    }

    @Override
    public List<Category> getAll() throws ExecutionException, InterruptedException {
        return categoryRepository.getAll();
    }

    @Override
    public Category update(Category category) {
        return categoryRepository.update(category);
    }

    @Override
    public void deleteById(String id) {
        categoryRepository.deleteById(id);
    }
}
