package com.kks.kksolution.dto.popup;

import com.kks.kksolution.entity.Popup;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.UUID;

@Getter
public class PopupDto {
    private UUID id;
    private String title;
    private String description;
    private LocalDateTime startAt;
    private LocalDateTime endAt;
    private Integer sequence;
    private String url;
    private String target;
    private String viewUrl;
    public PopupDto(Popup popup){
        this.id = popup.getId();
        this.title = popup.getTitle();
        this.description = popup.getDescription();
        this.startAt = popup.getStartAt();
        this.endAt = popup.getEndAt();
        this.sequence = popup.getSequence();
        this.url = popup.getUrl();
        this.target = popup.getTarget();
        this.viewUrl = popup.getViewUrl();
    }
}
