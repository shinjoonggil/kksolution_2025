package com.kks.kksolution.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Popup {
    @Id
    private UUID id;
    private String title;

    private LocalDateTime startAt;
    private LocalDateTime endAt;

    private Integer sequence;

    private String url;
    private String target;
    private String alt;

    @ManyToOne(fetch = FetchType.LAZY)
    private UploadFile uploadFile;


    @Column(columnDefinition = "MEDIUMTEXT")
    private String description;


    @ManyToOne(fetch = FetchType.LAZY)
    private User createBy;

    @CreationTimestamp
    private LocalDateTime createAt;
    @UpdateTimestamp
    private LocalDateTime updateAt;
    private String createIp;
    private String updateIp;
}
