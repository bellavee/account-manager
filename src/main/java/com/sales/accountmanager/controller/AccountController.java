package com.sales.accountmanager.controller;

import com.sales.accountmanager.constant.PathConstant;
import io.github.wimdeblauwe.htmx.spring.boot.mvc.HtmxResponse;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(PathConstant.ACCOUNT)
public class AccountController {

    @GetMapping
    public String index(Model model) {
        model.addAttribute("activePage", "account");
        return "account/index";
    }

    @GetMapping(PathConstant.FORM)
    public HtmxResponse openForm(Model model) {
        return new HtmxResponse().addTemplate("account/fragments :: modal");
    }



}
