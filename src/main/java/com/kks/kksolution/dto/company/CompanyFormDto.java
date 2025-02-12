package com.kks.kksolution.dto.company;

import lombok.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class CompanyFormDto {
    private UUID id;
    private String name;
    private String businessNumber;

    private String address;
    private String zipcode;
    private String addressDetail;

    private String ownerName;
    private String contact;
    private String email;

    private String agentName;
    private String agentContact;
    private String agentEmail;


    private String description;
}
