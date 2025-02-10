package com.kks.kksolution.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.*;

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
    private String originName;
    private String contentType;
    private Long size;
    private LocalDateTime createAt;
}
