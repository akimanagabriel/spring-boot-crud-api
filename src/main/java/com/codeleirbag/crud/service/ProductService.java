package com.codeleirbag.crud.service;

import com.codeleirbag.crud.entity.ApiMessage;
import com.codeleirbag.crud.entity.Product;
import com.codeleirbag.crud.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {
    @Autowired
    private ProductRepository repository;

    public List<Product> getAllProducts() {
        return repository.findAll();
    }

    public ApiMessage create(Product product) {
        var createdProduct = repository.save(product);
        return new ApiMessage("Product created", createdProduct);
    }

    public Product getProductById(int id) {
        return repository.findById(id).orElse(null);
    }

    public List<Product> createMany(List<Product> productList) {
        return repository.saveAll(productList);
    }

    public ApiMessage deleteProductById(int id) {
        repository.deleteById(id);
        return new ApiMessage("Product removed", null);
    }

    public ApiMessage updateProduct(int id, Product newProduct) {
        try {
            Product existingProduct = repository.findById(id).orElseThrow(() -> new Exception("Product not found"));
            existingProduct.setName(newProduct.getName());
            existingProduct.setQuantity(newProduct.getQuantity());
            existingProduct.setPrice(newProduct.getPrice());
            repository.save(existingProduct);
            return new ApiMessage("product updated", existingProduct);
        } catch (Exception e) {
            return new ApiMessage(e.getMessage(), null);
        }
    }


}
