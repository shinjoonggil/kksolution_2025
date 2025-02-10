package com.kks.kksolution.vo.menu;

import com.kks.kksolution.entity.Menu;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class MenuVO {
    private String label;
    private String path;
    private List<MenuVO> children = new ArrayList<>();

    public void appendChildren(Menu menu) {
        this.children.add(new MenuVO(menu.getLabel(), menu.getPath(), new ArrayList<>()));
    }
}
