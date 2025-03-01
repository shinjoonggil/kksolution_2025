package com.kks.kksolution.dto.inquiry;

import com.kks.kksolution.constant.InquiryType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class QuickInquiryFormDto {
    private InquiryType type;
    private String companyName;
    private String agentName;
    private String agentContact;
    private String agentEmail;
    private String url;
    private String deadline;
    private String budget;
    private String content;

}
