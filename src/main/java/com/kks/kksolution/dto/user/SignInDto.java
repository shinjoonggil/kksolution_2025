package com.kks.kksolution.dto.user;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SignInDto {
    private String accountId;
    private String accountPassword;
}
