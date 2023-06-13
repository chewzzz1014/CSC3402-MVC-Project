package com.csc3402.dbproject.controller;
import com.csc3402.dbproject.model.Product;
import com.csc3402.dbproject.repository.ProductRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/products")
public class ProductController {
    private final ProductRepository productRepository;

    ProductController(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    // return all products
    @GetMapping("all")
    public String showAllProducts(Model model) {
        model.addAttribute("products", productRepository.findAll());
        model.addAttribute("category", "all");
        return "product";
    }

    // return products of men category
    @GetMapping("men")
    public String showAllMenProducts(Model model) {
        List<Product> products = (List<Product>) productRepository.filterProductByCategory(1);
        model.addAttribute("products", products);
        model.addAttribute("category", "men");
        return "product";
    }

    // return products of women category
    @GetMapping("women")
    public String showAllWomenProducts(Model model) {
        List<Product> products = (List<Product>) productRepository.filterProductByCategory(2);
        model.addAttribute("category", "women");
        model.addAttribute("products", products);
        return "product";
    }
}
