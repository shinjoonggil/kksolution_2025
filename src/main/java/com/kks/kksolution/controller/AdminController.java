package com.kks.kksolution.controller;

import com.kks.kksolution.dto.company.CompanyFormDto;
import com.kks.kksolution.service.CompanyService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.UUID;

@Controller
@RequestMapping("admin")
@RequiredArgsConstructor
public class AdminController {
    private static final Logger log = LoggerFactory.getLogger(AdminController.class);

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
    public String updateCompanyForm(@PathVariable UUID id, Model model){
        model.addAttribute("menuLabel" , "company.list");
        model.addAttribute("item", companyService.getCompany(id));
        return "admin/company/form";
    }
    private final CompanyService companyService;
    @PostMapping("company-form")
    public String companyProcess(@ModelAttribute CompanyFormDto form , MultipartFile[] uploadFiles){
        log.info(form.toString());
        for (MultipartFile uploadFile : uploadFiles) {
            log.info(uploadFile.getOriginalFilename());
            log.info(uploadFile.getName());
        }
        UUID id = companyService.companyProcess(form);
        return "redirect:company-form/" + id;
    }

}
