package com.kks.kksolution.config;

import com.kks.kksolution.vo.common.MessageVO;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.context.MessageSource;
import org.springframework.context.NoSuchMessageException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.servlet.LocaleResolver;

import java.util.Locale;


@ControllerAdvice
@RequiredArgsConstructor
public class MessageAdvice {
    private final HttpServletRequest request;
    private final MessageSource messageSource;
    private final LocaleResolver localeResolver;
    @ModelAttribute("message")
    public MessageVO setMessage() {
        MessageVO sessionMessage = (MessageVO) request.getSession().getAttribute("message");

        if(sessionMessage != null) {
            Locale locale = localeResolver.resolveLocale(request);
            String message;
            try {
                message = messageSource.getMessage(sessionMessage.getMessage(), null, locale);
            }catch (NoSuchMessageException e) {
                message = sessionMessage.getMessage();
            }
            sessionMessage.setMessage(message);
            request.getSession().removeAttribute("message");
            return sessionMessage;
        }else {
            return null;
        }


    }
}
