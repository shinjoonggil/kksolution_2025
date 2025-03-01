package com.kks.kksolution.controller;

import com.kks.kksolution.service.PopupService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@RequiredArgsConstructor
public class MainController {
    private final PopupService popupService;
    @GetMapping("")
    public String index(Model model) {
        model.addAttribute("id","main");
//        model.addAttribute("popup",popupService.getVisiblePopup());
        return "index";
    }
}
