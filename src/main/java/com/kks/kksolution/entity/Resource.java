package com.kks.kksolution.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
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
public class Resource {
    @Id
    private UUID id;
    @ManyToOne(fetch = FetchType.LAZY)
    private ResourceGroup group;

    @ManyToOne(fetch = FetchType.LAZY)
    private UploadFile resource;
    private String title;

    @ManyToOne(fetch = FetchType.LAZY)
    private User createBy;
    @CreationTimestamp
    private LocalDateTime createAt;
    @UpdateTimestamp
    private LocalDateTime updateAt;
    private String createIp;
    private String updateIp;
}
