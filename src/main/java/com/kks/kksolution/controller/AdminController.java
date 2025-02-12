package com.kks.kksolution.controller;

import com.kks.kksolution.dto.common.DeleteFormDto;
import com.kks.kksolution.dto.company.CompanyFormDto;
import com.kks.kksolution.dto.content.ContentFormDto;
import com.kks.kksolution.dto.filter.CompanyFilterDto;
import com.kks.kksolution.dto.filter.ContentFilterDto;
import com.kks.kksolution.dto.filter.PopUpFilterDto;
import com.kks.kksolution.dto.popup.PopupFormDto;
import com.kks.kksolution.service.CompanyService;
import com.kks.kksolution.service.ContentService;
import com.kks.kksolution.service.PopupService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

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

    @GetMapping("")
    public String dashboard(Model model) {
        model.addAttribute("menuLabel", "home");
        return "admin/dashboard";
    }
//    Company Controller Start
    @GetMapping("company")
    public String getCompanyList(Model model, CompanyFilterDto filter) {
        model.addAttribute("menuLabel", "company.list");
        model.addAttribute("path","/admin/company");
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
        model.addAttribute("menuLabel", "content.list");
        model.addAttribute("path","/admin/content");
        model.addAttribute("items", contentService.getContents(filter));
        return "admin/content/list";
    }
    @GetMapping("content-form")
    public String createContentForm(Model model) {
        model.addAttribute("menuLabel", "content.list");
        model.addAttribute("item", contentService.getEmptyContent());
        return "admin/content/form";
    }
    @GetMapping("content-form/{id}")
    public String updateContentForm(Model model , @PathVariable UUID id) {
        model.addAttribute("menuLabel", "content.list");
        model.addAttribute("item", contentService.getContent(id));
        return "admin/content/form";
    }
    @PostMapping("content-form")
    public String contentProcess(@ModelAttribute ContentFormDto form) {
        UUID id = contentService.contentProcess(form);
        return "redirect:content-form/" + id;
    }
    @PostMapping("content-delete")
    public String contentDelete(@ModelAttribute DeleteFormDto form ) {
        contentService.deleteProcess(form);
        return "redirect:content";
    }
//    Content Controller End



//    Popup Controller Start

    @GetMapping("popup")
    public String getPopupList(Model model, PopUpFilterDto filter) {
        model.addAttribute("menuLabel", "popup.list");
        model.addAttribute("path","/admin/popup");
        model.addAttribute("items", popupService.getPageList(filter));
        return "admin/content/list";
    }
    @GetMapping("popup-form")
    public String createPopupForm(Model model) {
        model.addAttribute("menuLabel", "popup.list");
        model.addAttribute("item", popupService.getEmptyData());
        return "admin/popup/form";
    }
    @GetMapping("popup-form/{id}")
    public String updatePopupForm(Model model , @PathVariable UUID id) {
        model.addAttribute("menuLabel", "popup.list");
        model.addAttribute("item", popupService.getData(id));
        return "admin/popup/form";
    }
    @PostMapping("popup-form")
    public String popupProcess(@ModelAttribute PopupFormDto form) {
        UUID id = popupService.dataProcess(form);
        return "redirect:popup-form/" + id;
    }
    @PostMapping("popup-delete")
    public String popupDelete(@ModelAttribute DeleteFormDto form , Locale locale) {
        popupService.deleteProcess(form);
        return "redirect:popup";
    }
//    Popup Controller End
}
