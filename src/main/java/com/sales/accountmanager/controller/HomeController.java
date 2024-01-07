package com.sales.accountmanager.controller;

import com.sales.accountmanager.service.ProductService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    private final ProductService productService;

    public HomeController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping
    public String index(Model model) {
        model.addAttribute("activePage", "home");
        model.addAttribute("products", productService.findAll());
        return "home/index";
    }



}
