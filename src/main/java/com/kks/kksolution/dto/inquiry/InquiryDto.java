package com.kks.kksolution.dto.inquiry;

import com.kks.kksolution.entity.Inquiry;
import com.kks.kksolution.enumeration.InquiryType;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.UUID;

@Getter
public class InquiryDto {
    private UUID id;
    private String title;
    private String content;
    private InquiryType type;
    private String agentName;
    private String agentContact;
    private String agentEmail;
    private String url;
    private String description;

    private LocalDateTime createAt;
    public InquiryDto(Inquiry inquiry) {
        this.id = inquiry.getId();
        this.title = inquiry.getTitle();
        this.content = inquiry.getContent();
        this.url = inquiry.getUrl();
        this.type=inquiry.getInquiryType();
        this.agentName = inquiry.getAgentName();
        this.agentContact = inquiry.getAgentContact();
        this.agentEmail = inquiry.getAgentEmail();
        this.description = inquiry.getDescription();
        this.createAt = inquiry.getCreateAt();
    }
}
