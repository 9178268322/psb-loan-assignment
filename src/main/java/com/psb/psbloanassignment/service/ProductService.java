package com.psb.psbloanassignment.service;

import com.psb.psbloanassignment.model.Product;
import org.springframework.data.domain.Page;

import java.util.List;

public interface ProductService {

    public List<Product> getAllProducts();

    public Product addProduct(Product product);

    public Product getProduct(Long id);

    public void deleteProduct(Long id);

    public List<Product> getAllProdcutsByNameAsc();

    public List<Product> searchProductByName(String name);

    Page<Product> findPaginated(int pageNo, int pageSize, String sortField, String sortDirection);

}
