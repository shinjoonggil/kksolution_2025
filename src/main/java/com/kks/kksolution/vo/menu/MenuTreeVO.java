package com.kks.kksolution.vo.menu;

import com.kks.kksolution.entity.Menu;

import com.kks.kksolution.constant.MenuType;
import lombok.Getter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Getter
public class MenuTreeVO {
    private List<MenuVO> header;
    private List<MenuVO> content;
    public MenuTreeVO(List<Menu> menuList){
        List<Menu> headerMenus = menuList.stream()
                .filter(menu -> menu.getType() == MenuType.HEADER)
                .toList();
        List<Menu> contentMenus = menuList.stream()
                .filter(menu -> menu.getType() == MenuType.CONTENT)
                .toList();
        this.header = buildTree(headerMenus);
        this.content = buildTree(contentMenus);
    }


    private List<MenuVO> buildTree(List<Menu> menus) {
        Map<Long, MenuVO> menuMap = new HashMap<>();
        List<MenuVO> rootMenus = new ArrayList<>();

        // 1. 모든 메뉴를 MenuVO로 변환하여 저장
        for (Menu menu : menus) {
            menuMap.put(menu.getId(), new MenuVO(menu.getLabel(), menu.getPath(), new ArrayList<>()));
        }

        // 2. 부모-자식 관계 정리
        for (Menu menu : menus) {
            MenuVO menuVO = menuMap.get(menu.getId());
            if (menu.getParentId() == null || menu.getParentId() == 0) {
                rootMenus.add(menuVO);
            } else {
                MenuVO parentVO = menuMap.get(menu.getParentId());
                if (parentVO != null) {
                    parentVO.appendChildren(menu);
                }
            }
        }

        return rootMenus;
    }
}
