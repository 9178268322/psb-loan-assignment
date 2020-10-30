package com.psb.psbloanassignment.controller;

import com.psb.psbloanassignment.model.Product;
import com.psb.psbloanassignment.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/api")
public class ProductController {

    @Autowired
    private ProductService productService;

    @GetMapping("/products")
    public String getProducts(Model model) {
        List<Product> productList = productService.getAllProdcutsByNameAsc();
        model.addAttribute("products", productList);
        return "list-products";
    }

    @GetMapping("/showFormForAdd")
    public String showFormForAdd(Model model) {
        Product product = new Product();
        model.addAttribute("product", product);
        return "product-form";
    }

    @GetMapping("/showFormForUpdate")
    public String showFormForUpdate(@RequestParam("id") Long id, Model model) {
        Product product = productService.getProduct(id);
        model.addAttribute("product", product);
        return "product-form";
    }
    @GetMapping("/products/{productId}")
    public Product getProduct(@PathVariable Long productId) {
        return productService.getProduct(productId);
    }

    @PostMapping("/products")
    public String addProduct(@ModelAttribute("product") Product product) {
        productService.addProduct(product);
        return "redirect:/api/products";
    }

    @GetMapping("/delete")
    public String deleteProduct(@RequestParam("id") Long id) {
        productService.deleteProduct(id);
        return "redirect:/api/products";
    }

}
