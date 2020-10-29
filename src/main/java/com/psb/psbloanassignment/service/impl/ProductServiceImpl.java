package com.psb.psbloanassignment.service.impl;

import com.psb.psbloanassignment.model.Product;
import com.psb.psbloanassignment.repository.ProductRepository;
import com.psb.psbloanassignment.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductRepository productRepository;

    @Override
    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    @Override
    public Product addProduct(Product product) {
        return productRepository.save(product);
    }

    @Override
    public Product getProduct(Long id) {
        Optional<Product> product = productRepository.findById(id);
        Product finalProduct = product.get();

        if (finalProduct == null) {
            throw new IllegalArgumentException("Product not found");
        }
        return finalProduct;
    }

    @Override
    public Product updateProduct(Product product, Long id) {
        Optional<Product> product1 = productRepository.findById(id);
        Product p = product1.get();
        p.setName(product.getName());
        p.setPrice(product.getPrice());

        final Product finalProduct = productRepository.save(p);
        return finalProduct;
    }

    @Override
    public void deleteProduct(Long id) {
        Optional<Product> product = productRepository.findById(id);
        Product finalProduct = product.get();
        productRepository.delete(finalProduct);
    }
}
