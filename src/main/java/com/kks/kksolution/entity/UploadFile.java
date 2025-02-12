package com.kks.kksolution.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UploadFile {
    @Id
    private UUID id;
    private UUID groupId;
    private String origin;
    private String contentType;
    private Long size;
    @CreationTimestamp
    private LocalDateTime createAt;
}
