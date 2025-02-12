package com.kks.kksolution.dto.popup;

import lombok.*;

import java.time.LocalDateTime;
import java.util.UUID;
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
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
