package com.kks.kksolution.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.kks.kksolution.entity.Menu;
import com.kks.kksolution.repository.MenuRepository;
import com.kks.kksolution.vo.menu.MenuTreeVO;
import com.kks.kksolution.vo.menu.MenuVO;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.util.AntPathMatcher;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

@ControllerAdvice
@RequiredArgsConstructor
public class GlobalModelAdvice {
    private final static String GLOBAL_MENU_ATTRIBUTE_KEY = "globalMenu";
    private final static String ADMIN_MENU_ATTRIBUTE_KEY = "adminMenu";
    private final MenuRepository menuRepository;
    private final HttpServletRequest request;

    @ModelAttribute(GLOBAL_MENU_ATTRIBUTE_KEY)
    public MenuTreeVO setMainGlobalMenuTree() {
        if (isMainRun()) {
            List<Menu> menuList = menuRepository.findGlobalMenuList();
            return new MenuTreeVO(menuList);
        }
        return null;
    }

    private Boolean isMainRun() {
        String uri = request.getRequestURI();
        AntPathMatcher pathMatcher = new AntPathMatcher();
        String[] skipPathPatterns = {"/assets/**", "/favicon.ico", "/robots.txt", "/sitemap.xml", "/admin/**"};
        for (String pattern : skipPathPatterns) {
            if (pathMatcher.match(pattern, uri)) {
                return false;
            }
        }
        return true;
    }

    private final ResourceLoader resourceLoader;

    private final static String MENU_JSON_PATH = "classpath:static/json/admin-menu.json";

    @ModelAttribute(ADMIN_MENU_ATTRIBUTE_KEY)
    public List<MenuVO> setAdminGlobalMenuTree() throws IOException {
        if (request.getRequestURI().startsWith("/admin")) {
            ObjectMapper objectMapper = new ObjectMapper();
            Resource resource = resourceLoader.getResource(MENU_JSON_PATH);
            try (InputStream inputStream = resource.getInputStream()) {
                return objectMapper.readValue(inputStream,
                        objectMapper.getTypeFactory().constructCollectionType(List.class, MenuVO.class));
            }
        }
        return null;
    }

    @ModelAttribute("menuHide")
    public String bodyClass(HttpServletRequest request) {
        // 요청에서 모든 쿠키 가져오기
        Cookie[] cookies = request.getCookies();

        // 쿠키 배열을 순회하면서 aside-hide 쿠키 찾기
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if(cookie.getName().equals("menu-status")) {
                    return cookie.getValue();
                }
            }
        }
        return "";
    }
}
