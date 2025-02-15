package com.kks.kksolution.controller;

import com.kks.kksolution.entity.Content;
import com.kks.kksolution.service.ContentService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("content")
@RequiredArgsConstructor
public class ContentController {
    private final ContentService contentService;
    @GetMapping("{label}")
    public String content(@PathVariable String label , Model model){
        model.addAttribute("content",contentService.getContent(label));
        return "view/content";
    }
}
