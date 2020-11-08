package com.psb.psbloanassignment.service.impl;

import com.psb.psbloanassignment.model.Product;
import com.psb.psbloanassignment.repository.ProductRepository;
import com.psb.psbloanassignment.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
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
    public List<Product> getAllProdcutsByNameAsc() {
        return productRepository.findAllByOrderByNameAsc();
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
    public List<Product> searchProductByName(String name) {
        List<Product> result = null;

        if (name != null && name.trim().length() > 0) {
            result = productRepository.findByNameContainsAllIgnoreCase(name);
        }
        else {
            result = getAllProducts();
        }
        return result;
    }

    @Override
    public Product addProduct(Product product) {
        return productRepository.save(product);
    }

    @Override
    public void deleteProduct(Long id) {
        Optional<Product> product = productRepository.findById(id);
        Product finalProduct = product.get();
        productRepository.delete(finalProduct);
    }

    @Override
    public Page<Product> findPaginated(int pageNo, int pageSize, String sortField, String sortDirection) {
        Sort sort = sortDirection.equalsIgnoreCase(Sort.Direction.ASC.name()) ?
                Sort.by(sortField).ascending() : Sort.by(sortField).descending();

        Pageable pageable = PageRequest.of(pageNo - 1, pageSize, sort);
        return productRepository.findAll(pageable);
    }
}
