package com.kks.kksolution.entity;

import com.kks.kksolution.constant.InquiryType;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
public class Inquiry {
    @Id
    private UUID id;
    private String companyName;

    @Enumerated(EnumType.STRING)
    private InquiryType inquiryType;

    private String agentName;
    private String agentContact;
    private String agentEmail;
    private String url;
    private String deadline;
    private String budget;

    @Column(columnDefinition = "TEXT")
    private String content;
    @Column(columnDefinition = "MEDIUMTEXT")
    private String description;


    @CreationTimestamp
    private LocalDateTime createAt;
    @UpdateTimestamp
    private LocalDateTime updateAt;
    private String createIp;
    private String updateIp;

}
