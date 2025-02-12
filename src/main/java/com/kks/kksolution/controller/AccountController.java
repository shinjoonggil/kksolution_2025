package com.kks.kksolution.controller;

import com.kks.kksolution.dto.user.RegisterUserDto;
import com.kks.kksolution.service.AccountService;
import com.kks.kksolution.vo.common.MessageVO;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("account")
@RequiredArgsConstructor
public class AccountController {
    private final AccountService accountService;
    private final HttpServletRequest request;

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
    public String signUpProcess(@ModelAttribute RegisterUserDto registerUserDto) {
        accountService.registerUser(registerUserDto);
        request.getSession().setAttribute("message", MessageVO.SUCCESS("account.success.signup"));
        return "redirect:/account/signIn";
    }
}
