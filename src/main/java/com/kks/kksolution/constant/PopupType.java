package com.kks.kksolution.constant;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum PopupType {
    _BLANK("popup.target.blank"),
    _SELF("popup.target.self");

    private String message;

}
