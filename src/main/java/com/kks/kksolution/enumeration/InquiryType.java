package com.kks.kksolution.enumeration;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum InquiryType {
    HOMEPAGE("inquiry.homepage"),
    MALL("inquiry.mall"),
    APP("inquiry.app"),
    DESIGN("inquiry.design"),
    MARKETING("inquiry.marketing"),
    VIDEO("inquiry.video"),
    SOLUTION("inquiry.solution"),
    ;
    
    private String message;
}
