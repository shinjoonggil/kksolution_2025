package com.kks.kksolution.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("auth")
public class AuthController {
    @GetMapping("signIn")
    public String signIn(Model model) {
        model.addAttribute("title", "로그인");
        return "auth/signIn";
    }
}
