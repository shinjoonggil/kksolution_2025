package com.kks.kksolution.controller;

import com.kks.kksolution.dto.company.CompanyFormDto;
import com.kks.kksolution.service.CompanyService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("admin")
@RequiredArgsConstructor
public class AdminController {
    @GetMapping("")
    public String dashboard(Model model){
        model.addAttribute("menuLabel" , "home");
        return "admin/dashboard";
    }
    @GetMapping("company")
    public String companyList(Model model){
        model.addAttribute("menuLabel" , "company.list");
        return "admin/company/list";
    }
    @GetMapping("company-form")
    public String createCompanyForm(Model model){
        model.addAttribute("menuLabel" , "company.list");
        return "admin/company/form";
    }
    @GetMapping("company-form/{id}")
    public String updateCompanyForm(Model model){
        model.addAttribute("menuLabel" , "company.list");
        return "admin/company/form";
    }
    private final CompanyService companyService;
    @PostMapping("company-form")
    public String companyProcess( @ModelAttribute CompanyFormDto form){
        companyService.companyProcess(form);
        return "redirect:company-form";
    }

}
