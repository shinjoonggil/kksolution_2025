package com.kks.kksolution.constant;

import lombok.AllArgsConstructor;
import lombok.Getter;
@Getter
@AllArgsConstructor
public enum UserRole {
    ROLE_USER("role.user"),
    ROLE_ADMIN("role.admin"),
    ROLE_SUPER("role.super");
    private final String label;
}
