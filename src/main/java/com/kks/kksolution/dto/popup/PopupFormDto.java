package com.kks.kksolution.dto.popup;

import com.kks.kksolution.dto.common.CommonFormDto;
import com.kks.kksolution.entity.UploadFile;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDateTime;
import java.util.UUID;
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class PopupFormDto extends CommonFormDto {
    private UUID id;
    private String title;
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm")
    private LocalDateTime startAt;
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm")
    private LocalDateTime endAt;
    private Integer sequence;
    private String url;
    private String target;
    private String description;
    private String alt;
    private MultipartFile uploadFile;
    private Boolean deleteFile=false;
}
