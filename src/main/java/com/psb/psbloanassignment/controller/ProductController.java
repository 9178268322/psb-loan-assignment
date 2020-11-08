package com.psb.psbloanassignment.controller;

import com.psb.psbloanassignment.model.Product;
import com.psb.psbloanassignment.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
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
        return findPaginated(1, "name", "asc", model);
    }

    @GetMapping("/products/page")
    public String findPaginated(@RequestParam("pageNo") int pageNo,
                                @RequestParam("sortField") String sortField,
                                @RequestParam("sortDir") String sortDir,
                                 Model model) {
        int pageSize = 5;
        Page<Product> page = productService.findPaginated(pageNo, pageSize, sortField, sortDir);
        List<Product> productList = page.getContent();

        model.addAttribute("currentPage", pageNo);
        model.addAttribute("totalPages", page.getTotalPages());
        model.addAttribute("totalItems", page.getTotalElements());

        model.addAttribute("sortField", sortField);
        model.addAttribute("sortDir", sortDir);
        model.addAttribute("reverseSortDir", sortDir.equals("asc") ? "desc" : "asc");

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
    @GetMapping("/search")
    public String getProductByName(@RequestParam("name") String name, Model model) {
        List<Product> productList = productService.searchProductByName(name);
        model.addAttribute("products", productList);
        return "list-products";
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
