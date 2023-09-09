package com.codeleirbag.crud.controller;

import com.codeleirbag.crud.entity.ApiMessage;
import com.codeleirbag.crud.entity.Product;
import com.codeleirbag.crud.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/product")
public class ProductController {
    @Autowired
    private ProductService productService;

    @GetMapping
    public List<Product> index() {
        return productService.getAllProducts();
    }

    @GetMapping("/{id}")
    public Product getProductById(@PathVariable int id) {
        return productService.getProductById(id);
    }

    @PostMapping
    public ApiMessage create(@RequestBody Product product) {
        return productService.create(product);
    }

    @PutMapping("/{identifier}")
    public ApiMessage update(@PathVariable int identifier, @RequestBody Product product) {
        return productService.updateProduct(identifier, product);
    }

    @DeleteMapping("/{id}")
    public ApiMessage delete(@PathVariable int id) {
        return productService.deleteProductById(id);
    }

    @PostMapping("/multiple")
    public List<Product> createMany(@RequestBody List<Product> productList) {
        return productService.createMany(productList);
    }

}
