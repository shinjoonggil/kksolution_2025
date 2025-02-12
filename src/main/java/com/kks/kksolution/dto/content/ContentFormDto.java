package com.kks.kksolution.dto.content;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ContentFormDto {
    private UUID id;
    private String title;
    private String path;
    private String content;
}
