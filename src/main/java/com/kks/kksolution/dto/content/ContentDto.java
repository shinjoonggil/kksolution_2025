package com.kks.kksolution.dto.content;

import com.kks.kksolution.entity.Content;
import com.kks.kksolution.entity.User;
import lombok.Getter;

import java.util.UUID;

@Getter
public class ContentDto {
    private UUID id;
    private String title;
    private String label;
    private String content;
    private User createBy;
    public ContentDto(Content content) {
        this.id = content.getId();
        this.title = content.getTitle();
        this.label = content.getLabel();
        this.content = content.getContent();
        this.createBy = content.getCreateBy();
    }
}
