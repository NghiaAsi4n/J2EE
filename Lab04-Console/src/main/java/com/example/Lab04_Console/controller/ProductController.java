package com.example.Lab04_Console.controller;

import com.example.Lab04_Console.model.Category;
import com.example.Lab04_Console.model.Product;
import com.example.Lab04_Console.service.ProductService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@Controller
@RequestMapping("/products")
public class ProductController {

    @Autowired
    private ProductService productService;

    @GetMapping("")
    public String index(Model model) {
        model.addAttribute("listproduct", productService.getAll());
        return "product/products";
    }

    @GetMapping("/create")
    public String create(Model model) {
        model.addAttribute("product", new Product());
        model.addAttribute("categories", productService.getAllCategories());
        return "product/create";
    }

    @PostMapping("/create")
    public String create(@Valid Product newProduct,
            BindingResult result,
            @RequestParam("imageProduct") MultipartFile imageProduct,
            Model model) {
        if (result.hasErrors()) {
            model.addAttribute("product", newProduct);
            model.addAttribute("categories", productService.getAllCategories());
            return "product/create";
        }

        // Xử lý logic gán category thủ công do form trả về ID
        if (newProduct.getCategory() != null) {
            Category c = productService.getCategory(newProduct.getCategory().getId());
            newProduct.setCategory(c);
        }

        productService.updateImage(newProduct, imageProduct);
        productService.add(newProduct);
        return "redirect:/products";
    }
}