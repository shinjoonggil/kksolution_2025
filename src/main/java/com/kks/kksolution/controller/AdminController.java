package com.kks.kksolution.controller;

import com.kks.kksolution.dto.common.DeleteFormDto;
import com.kks.kksolution.dto.company.CompanyFormDto;
import com.kks.kksolution.dto.content.ContentFormDto;
import com.kks.kksolution.dto.filter.*;
import com.kks.kksolution.dto.popup.PopupFormDto;
import com.kks.kksolution.dto.inquiry.InquiryFormDto;
import com.kks.kksolution.dto.portfolio.PortfolioFormDto;
import com.kks.kksolution.dto.resource.ResourceFormDto;
import com.kks.kksolution.constant.InquiryType;
import com.kks.kksolution.constant.PopupType;
import com.kks.kksolution.constant.PortfolioType;
import com.kks.kksolution.service.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.Locale;
import java.util.UUID;

@Controller
@RequestMapping("admin")
@RequiredArgsConstructor
@Slf4j
public class AdminController {

    private final CompanyService companyService;
    private final ContentService contentService;
    private final PopupService popupService;
    private final PortfolioService portfolioService;
    private final InquiryService inquiryService;
    private final ResourceService resourceService;

    @GetMapping("")
    public String dashboard(Model model) {
        model.addAttribute("menuLabel", "home");
        return "admin/dashboard";
    }

    //    Company Controller Start
    @GetMapping("company")
    public String getCompanyList(Model model, CompanyFilterDto filter) {
        model.addAttribute("menuLabel", "company.list");
        model.addAttribute("path", "/admin/company");
        model.addAttribute("items", companyService.getCompanies(filter));
        return "admin/company/list";
    }

    @GetMapping("company-form")
    public String createCompanyForm(Model model) {
        model.addAttribute("menuLabel", "company.list");
        model.addAttribute("item", companyService.getEmptyCompany());
        return "admin/company/form";
    }

    @GetMapping("company-form/{id}")
    public String updateCompanyForm(@PathVariable UUID id, Model model) {
        model.addAttribute("menuLabel", "company.list");
        model.addAttribute("item", companyService.getCompany(id));
        return "admin/company/form";
    }

    @PostMapping("company-form")
    public String companyProcess(@ModelAttribute CompanyFormDto form) {
        UUID id = companyService.companyProcess(form);
        return "redirect:company-form/" + id;
    }
//    Company Controller End


//    Content Controller Start

    @GetMapping("content")
    public String getContentList(Model model, ContentFilterDto filter) {
        model.addAttribute("menuLabel", "content.content");
        model.addAttribute("path", "/admin/content");
        model.addAttribute("items", contentService.getContents(filter));
        return "admin/content/list";
    }

    @GetMapping("content-form")
    public String createContentForm(Model model) {
        model.addAttribute("menuLabel", "content.content");
        model.addAttribute("item", contentService.getEmptyContent());
        return "admin/content/form";
    }

    @GetMapping("content-form/{id}")
    public String updateContentForm(Model model, @PathVariable UUID id) {
        model.addAttribute("menuLabel", "content.content");
        model.addAttribute("item", contentService.getContent(id));
        return "admin/content/form";
    }

    @PostMapping("content-form")
    public String contentProcess(@ModelAttribute ContentFormDto form) {
        UUID id = contentService.contentProcess(form);
        return "redirect:content-form/" + id;
    }

    @PostMapping("content-delete")
    public String contentDelete(@ModelAttribute DeleteFormDto form) {
        contentService.deleteProcess(form);
        return "redirect:content";
    }
//    Content Controller End


//    Popup Controller Start

    @GetMapping("popup")
    public String getPopupList(Model model, PopUpFilterDto filter) {
        model.addAttribute("menuLabel", "content.popup");
        model.addAttribute("path", "/admin/popup");
        model.addAttribute("items", popupService.getPageList(filter));
        return "admin/popup/list";
    }

    @GetMapping("popup-form")
    public String createPopupForm(Model model) {
        model.addAttribute("menuLabel", "content.popup");
        model.addAttribute("item", popupService.getEmptyData());
        model.addAttribute("types", Arrays.asList(PopupType.values()));
        return "admin/popup/form";
    }

    @GetMapping("popup-form/{id}")
    public String updatePopupForm(Model model, @PathVariable UUID id) {
        model.addAttribute("menuLabel", "content.popup");
        model.addAttribute("item", popupService.getData(id));
        model.addAttribute("types", Arrays.asList(PopupType.values()));
        return "admin/popup/form";
    }

    @PostMapping("popup-form")
    public String popupProcess(@ModelAttribute PopupFormDto form) {
        UUID id = popupService.dataProcess(form);
        return "redirect:popup-form/" + id;
    }

    @PostMapping("popup-delete")
    public String popupDelete(@ModelAttribute DeleteFormDto form, Locale locale) {
        popupService.deleteProcess(form);
        return "redirect:popup";
    }
//    Popup Controller End


    //    Portfolio Controller Start
    @GetMapping("portfolio")
    public String getPortfolioList(Model model, PortfolioFilterDto filter) {
        model.addAttribute("menuLabel", "content.portfolio");
        model.addAttribute("path", "/admin/portfolio");
        model.addAttribute("items", portfolioService.getPageList(filter));
        return "admin/portfolio/list";
    }

    @GetMapping("portfolio-form")
    public String createPortfolioForm(Model model) {
        model.addAttribute("menuLabel", "content.portfolio");
        model.addAttribute("item", portfolioService.getEmptyData());
        model.addAttribute("types", Arrays.asList(PortfolioType.values()));
        return "admin/portfolio/form";
    }

    @GetMapping("portfolio-form/{id}")
    public String updatePortfolioForm(Model model, @PathVariable UUID id) {
        model.addAttribute("menuLabel", "content.portfolio");
        model.addAttribute("item", portfolioService.getData(id));
        model.addAttribute("types", Arrays.asList(PortfolioType.values()));
        return "admin/portfolio/form";
    }

    @PostMapping("portfolio-form")
    public String portfolioProcess(@ModelAttribute PortfolioFormDto form) {
        UUID id = portfolioService.dataProcess(form);
        return "redirect:portfolio-form/" + id;
    }

    @PostMapping("portfolio-delete")
    public String portfolioDelete(@ModelAttribute DeleteFormDto form, Locale locale) {
        portfolioService.deleteProcess(form);
        return "redirect:portfolio";
    }
//    Portfolio Controller End

    //    Inquiry Controller Start
    @GetMapping("inquiry")
    public String getInquiryList(Model model, InquiryFilterDto filter) {
        model.addAttribute("menuLabel", "content.inquiry");
        model.addAttribute("path", "/admin/inquiry");
        model.addAttribute("items", inquiryService.getPageList(filter));
        return "admin/inquiry/list";
    }

//    @GetMapping("inquiry-form")
//    public String createInquiryForm(Model model) {
//        model.addAttribute("menuLabel", "content.inquiry");
//        model.addAttribute("item", inquiryService.getEmptyData());
//        model.addAttribute("types", Arrays.asList(InquiryType.values()));
//        return "admin/inquiry/form";
//    }

    @GetMapping("inquiry-form/{id}")
    public String updateInquiryForm(Model model, @PathVariable UUID id) {
        model.addAttribute("menuLabel", "content.inquiry");
        model.addAttribute("item", inquiryService.getData(id));
        model.addAttribute("types", Arrays.asList(InquiryType.values()));
        return "admin/inquiry/form";
    }

    @PostMapping("inquiry-form")
    public String inquiryProcess(@ModelAttribute InquiryFormDto form) {
        UUID id = inquiryService.dataProcess(form);
        return "redirect:inquiry-form/" + id;
    }

    @PostMapping("inquiry-delete")
    public String inquiryDelete(@ModelAttribute DeleteFormDto form, Locale locale) {
        inquiryService.deleteProcess(form);
        return "redirect:inquiry";
    }
//    Inquiry Controller End


    //    Resource Controller Start
    @GetMapping("resource")
    public String getResourceList(Model model, ResourceFilterDto filter) {
        model.addAttribute("menuLabel", "content.resource");
        model.addAttribute("path", "/admin/resource");
        model.addAttribute("items", resourceService.getPageList(filter));
        return "admin/resource/list";
    }

    @GetMapping("resource-form")
    public String createResourceForm(Model model) {
        model.addAttribute("menuLabel", "content.resource");
        model.addAttribute("item", resourceService.getEmptyData());
        model.addAttribute("groups",resourceService.getAllGroup());
        return "admin/resource/form";
    }

    @GetMapping("resource-form/{id}")
    public String updateResourceForm(Model model, @PathVariable UUID id) {
        model.addAttribute("menuLabel", "content.resource");
        model.addAttribute("item", resourceService.getData(id));
        model.addAttribute("groups",resourceService.getAllGroup());
        return "admin/resource/form";
    }

    @PostMapping("resource-form")
    public String resourceProcess(@ModelAttribute ResourceFormDto form) {
        UUID id = resourceService.dataProcess(form);
        return "redirect:resource-form/" + id;
    }


    @PostMapping("resource-delete")
    public String resourceDelete(@ModelAttribute DeleteFormDto form) {
        resourceService.deleteData(form);
        return "redirect:resource";
    }
//    Resource Controller End

}
