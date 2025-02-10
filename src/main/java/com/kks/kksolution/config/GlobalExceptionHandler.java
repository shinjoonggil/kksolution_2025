package com.kks.kksolution.config;

import org.springframework.http.HttpStatus;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public String handleNotFound(Exception exception, Model model) {
        exception.printStackTrace();
        model.addAttribute("error", "페이지를 찾을 수 없습니다.");
        model.addAttribute("title", "404");
        return "error/404";  // 404 오류 페이지로 리다이렉트
    }
}
