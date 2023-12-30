package com.sales.accountmanager.controller;

import com.sales.accountmanager.constant.PathConstant;
import com.sales.accountmanager.dto.ProductDto;
import com.sales.accountmanager.service.ProductService;
import com.sales.accountmanager.utils.ControllerHelper;
import io.github.wimdeblauwe.htmx.spring.boot.mvc.HtmxResponse;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(PathConstant.PRODUCT)
public class ProductController {

    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping
    public String index(Model model) {
        model.addAttribute("activePage", "product");
        model.addAttribute("data", productService.findAll());
        return "product/index";
    }

    @GetMapping(PathConstant.FORM)
    public HtmxResponse openForm(Model model) {
        ProductDto form = new ProductDto();
        model.addAttribute("productForm", form);
        return new HtmxResponse().addTemplate("product/fragments :: modal");
    }

    @GetMapping(PathConstant.FORM + "/{id}")
    public HtmxResponse openModal(@PathVariable(name = "id") Long id, Model model) {
        model.addAttribute("productForm", productService.findById(id));
        return new HtmxResponse().addTemplate("product/fragments :: modal");
    }

    @PostMapping(PathConstant.SAVE)
    public HtmxResponse save(ProductDto dto, Model model) {
        return ControllerHelper.saveGeneric(
                dto,
                productService::save,
                model,
                "product/fragments",
                "Oke"
        );
    }

}
