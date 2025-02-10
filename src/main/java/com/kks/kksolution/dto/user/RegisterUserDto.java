package com.kks.kksolution.dto.user;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class RegisterUserDto {
    private String accountId;
    private String accountPassword;
    private String name;
    private String email;
    private String phone;
    private String contact;

}
