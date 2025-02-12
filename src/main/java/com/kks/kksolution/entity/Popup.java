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
    private String viewUrl;

    @ManyToOne
    private User createBy;

    @Column(columnDefinition = "TEXT")
    private String description;
    @CreationTimestamp
    private LocalDateTime createAt;
    @UpdateTimestamp
    private LocalDateTime updateAt;
    private String createIp;
    private String updateIp;
}
