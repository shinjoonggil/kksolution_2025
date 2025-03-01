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
        model.addAttribute("title","menu.company");
        model.addAttribute("id","company");
        return "view/company";
    }
    @GetMapping("service")
    public String serviceView(Model model){
        model.addAttribute("title","menu.service");
        model.addAttribute("id","service");
        return "view/service";
    }
    @GetMapping("portfolio")
    public String portfolioView(Model model){
        model.addAttribute("title","menu.portfolio");
        model.addAttribute("id","portfolio");
        return  "view/portfolio";
    }
    @GetMapping("contact")
    public String contactView(Model model){
        model.addAttribute("title","menu.contact");
        model.addAttribute("id","contact");
        return  "view/contact";
    }

}
