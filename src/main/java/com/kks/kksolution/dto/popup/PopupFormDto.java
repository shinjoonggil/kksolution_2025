package com.kks.kksolution.dto.popup;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.UUID;
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PopupFormDto {
    private UUID id;
    private String title;
    private LocalDateTime startAt;
    private LocalDateTime endAt;
    private Integer sequence;
    private String url;
    private String target;
    private String viewUrl;
    private String description;
}
