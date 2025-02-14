package com.kks.kksolution.enumeration;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum PortfolioType {
    HOMEPAGE("portfolio.homepage"),
    MALL("portfolio.mall"),
    APP("portfolio.app"),
    DESIGN("portfolio.design"),
    MARKETING("portfolio.marketing"),
    VIDEO("portfolio.video"),
    SOLUTION("portfolio.solution"),

    ;


    private String message;
}
