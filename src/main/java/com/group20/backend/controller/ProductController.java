package com.group20.backend.controller;

import com.group20.backend.model.Product;
import com.group20.backend.service.ProductService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.ExecutionException;

@RestController
@RequestMapping("api/product")
public class ProductController {

    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @PostMapping
    public void createProduct(@RequestBody Product product) {
        productService.create(product);
    }

    @GetMapping
    public Product getProductByID(@RequestParam String id) throws ExecutionException, InterruptedException {
        Optional<Product> product = productService.findById(id);
        return product.orElse(null);
    }

    @GetMapping("all")
    public List<Product> getAll() throws ExecutionException, InterruptedException {
        return productService.getAll();
    }


}
