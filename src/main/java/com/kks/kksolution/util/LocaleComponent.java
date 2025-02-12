package com.kks.kksolution.util;

import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Component;

import java.util.Locale;

@Component
public class LocaleComponent {
    public Locale getLocale() {
        return LocaleContextHolder.getLocale();
    }
}
