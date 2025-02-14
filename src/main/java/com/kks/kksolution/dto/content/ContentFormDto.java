package com.kks.kksolution.dto.content;

import com.kks.kksolution.dto.common.CommonFormDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ContentFormDto extends CommonFormDto {
    private UUID id;
    private String title;
    private String label;
    private String content;
}
