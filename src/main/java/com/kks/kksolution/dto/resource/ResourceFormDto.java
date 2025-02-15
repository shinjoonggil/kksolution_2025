package com.kks.kksolution.dto.resource;

import com.kks.kksolution.dto.common.CommonFormDto;
import lombok.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.UUID;
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class ResourceFormDto extends CommonFormDto {
    private UUID id;
    private String title;
    private String groupTitle;
    private MultipartFile resource;
}
