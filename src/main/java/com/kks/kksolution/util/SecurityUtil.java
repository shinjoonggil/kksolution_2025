package com.kks.kksolution.util;

import com.kks.kksolution.entity.User;
import com.kks.kksolution.entity.UserAccount;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

@Component
public class SecurityUtil {
    private static final Logger log = LoggerFactory.getLogger(SecurityUtil.class);

    public static User getCurrentUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        log.info(authentication.getPrincipal().toString());
        UserAccount account = (UserAccount) authentication.getPrincipal();
        return account.getUser();

    }
}
