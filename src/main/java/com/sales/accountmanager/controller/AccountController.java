package com.sales.accountmanager.controller;

import com.sales.accountmanager.constant.PathConstant;
import com.sales.accountmanager.dto.AccountDto;
import com.sales.accountmanager.service.AccountService;
import com.sales.accountmanager.service.ProductService;
import com.sales.accountmanager.utils.ControllerHelper;
import io.github.wimdeblauwe.htmx.spring.boot.mvc.HtmxResponse;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping(PathConstant.ACCOUNT)
public class AccountController {

    private final AccountService accountService;
    private final ProductService productService;

    public AccountController(AccountService accountService, ProductService productService) {
        this.accountService = accountService;
        this.productService = productService;
    }

    @GetMapping
    public String index(Model model) {
        model.addAttribute("activePage", "account");
        model.addAttribute("data", accountService.findAll());
        return "account/index";
    }

    @GetMapping(PathConstant.FORM)
    public HtmxResponse openForm(Model model) {
        AccountDto form = new AccountDto();
        model.addAttribute("accountForm", form);
        model.addAttribute("productList", productService.findAll());
        return new HtmxResponse().addTemplate("account/fragments :: modal");
    }

    @GetMapping(PathConstant.FORM + "/{id}")
    public HtmxResponse openModal(@PathVariable(name = "id") Long id, Model model) {
        AccountDto form = accountService.findById(id);
        model.addAttribute("accountForm", form);
        model.addAttribute("productList", productService.findAll());
        return new HtmxResponse().addTemplate("account/fragments :: modal");
    }

    @GetMapping(PathConstant.CREATE)
    public HtmxResponse create(Model model) {
        return ControllerHelper.saveGeneric(
                new AccountDto(),
                accountService::save,
                model,
                "account/fragments",
                "Oke"
        );
    }

    @PostMapping(PathConstant.SAVE)
    public HtmxResponse save(AccountDto dto, Model model) {
        return ControllerHelper.saveGeneric(
                dto,
                accountService::save,
                model,
                "account/fragments",
                "Oke"
        );
    }

    @GetMapping(PathConstant.PRODUCT + "/{oroductId}")
    public HtmxResponse getByProduct(@PathVariable("oroductId") Long oroductId, Model model) {
        List<AccountDto> list = accountService.findByProductId(oroductId);
        model.addAttribute("data", list);
        return new HtmxResponse().addTemplate("account/fragments :: table");
    }

}
