package com.psb.psbloanassignment.repository;

import com.psb.psbloanassignment.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

    // add a method to sort by product name in ascending order
    public List<Product> findAllByOrderByNameAsc();

    //add a method to serach product by name
    public List<Product> findByNameContainsAllIgnoreCase(String name);
}
