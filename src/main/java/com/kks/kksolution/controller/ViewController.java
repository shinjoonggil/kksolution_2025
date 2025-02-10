package com.kks.kksolution.controller;

import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("view")
@RequiredArgsConstructor
public class ViewController {
    private final HttpServletRequest request;
    @GetMapping("company")
    public String companyView(Model model){
        model.addAttribute("title","Company");
        return "view/company";
    }
    @GetMapping("service")
    public String serviceView(Model model){
        model.addAttribute("title","Service");
        return "view/service";
    }
    @GetMapping("portfolio")
    public String portfolioView(Model model){
        model.addAttribute("title","Portfolio");
        return  "view/portfolio";
    }
    @GetMapping("contact")
    public String contactView(Model model){
        model.addAttribute("title","Contact");
        return  "view/contact";
    }

}
