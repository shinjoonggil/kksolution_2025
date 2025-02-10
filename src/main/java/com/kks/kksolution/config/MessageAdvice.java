package com.kks.kksolution.config;

import com.kks.kksolution.vo.common.MessageVO;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;


@ControllerAdvice
@RequiredArgsConstructor
public class MessageAdvice {
    private final HttpServletRequest request;

    @ModelAttribute("message")
    public MessageVO setMessage() {
        MessageVO sessionMessage = (MessageVO) request.getSession().getAttribute("message");

        if(sessionMessage != null) {
            request.getSession().removeAttribute("message");
            return sessionMessage;
        }else {
            return null;
        }


    }
}
