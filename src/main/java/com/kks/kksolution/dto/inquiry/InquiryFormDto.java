package com.kks.kksolution.dto.inquiry;

import com.kks.kksolution.dto.common.CommonFormDto;
import com.kks.kksolution.constant.InquiryType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class InquiryFormDto extends CommonFormDto {
    private UUID id;
    private String title;
    private String content;

    private InquiryType type;

    private String agentName;
    private String agentContact;
    private String agentEmail;
    private String url;

    private String description;


}
