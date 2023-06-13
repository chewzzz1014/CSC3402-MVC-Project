package com.csc3402.dbproject.controller;
import com.csc3402.dbproject.model.Product;
import com.csc3402.dbproject.repository.ProductRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/products")
public class ProductController {
    private final ProductRepository productRepository;

    ProductController(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @GetMapping("all")
    public String showAllProducts(Model model) {
        model.addAttribute("products", productRepository.findAll());
        return "product";
    }
}
