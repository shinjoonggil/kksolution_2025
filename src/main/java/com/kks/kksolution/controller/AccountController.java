package com.kks.kksolution.controller;

import com.kks.kksolution.dto.user.RegisterUserDto;
import com.kks.kksolution.service.AccountService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("account")
@RequiredArgsConstructor
public class AccountController {
    private final AccountService accountService;

    @GetMapping("signIn")
    public String signIn(Model model) {
        model.addAttribute("title", "로그인");
        return "account/signIn";
    }

    @GetMapping("signUp")
    public String signUp(Model model) {
        model.addAttribute("title", "회원가입");
        return "account/signUp";
    }

    @PostMapping("signUp")
    public void signUpProcess(@ModelAttribute RegisterUserDto registerUserDto) {
        System.out.println(registerUserDto.toString());
        accountService.registerUser(registerUserDto);
    }
}
