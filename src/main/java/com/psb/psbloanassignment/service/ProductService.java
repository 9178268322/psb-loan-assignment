package com.psb.psbloanassignment.service;

import com.psb.psbloanassignment.model.Product;

import java.util.List;
import java.util.Optional;

public interface ProductService {

    public List<Product> getAllProducts();
    public Product addProduct(Product product);
    public Product getProduct(Long id);
    public Product updateProduct(Product product, Long id);
    public void deleteProduct(Long id);
    public List<Product> getAllProdcutsByNameAsc();

}
