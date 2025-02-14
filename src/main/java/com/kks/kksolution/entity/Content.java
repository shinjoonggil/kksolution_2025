package com.kks.kksolution.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
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
public class Content {
    @Id
    private UUID id;
    private String title;
    @Column(unique = true)
    private String label;
    @Column(columnDefinition = "MEDIUMTEXT")
    private String content;

    @ManyToOne
    private User createBy;

    @CreationTimestamp
    private LocalDateTime createAt;
    @UpdateTimestamp
    private LocalDateTime updateAt;
    private String createIp;
    private String updateIp;

}
