package com.group20.backend.service;

import com.group20.backend.model.Product;
import com.group20.backend.repository.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.ExecutionException;

@Service
public class ProductService implements BasicCrudService<Product> {

    private final ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public boolean create(Product product) {

        return productRepository.create(product);
    }

    @Override
    public Optional<Product> findById(String id) throws ExecutionException, InterruptedException {
        return productRepository.findById(id);
    }

    @Override
    public List<Product> getAll() throws ExecutionException, InterruptedException {
        return productRepository.getAll();
    }

    @Override
    public Product update(Product product) {
        return productRepository.update(product);
    }

    @Override
    public void deleteById(String id) {
        productRepository.deleteById(id);
    }
}
