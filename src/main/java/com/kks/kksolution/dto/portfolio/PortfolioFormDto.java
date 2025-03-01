package com.kks.kksolution.dto.portfolio;

import com.kks.kksolution.dto.common.CommonFormDto;
import com.kks.kksolution.constant.PortfolioType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class PortfolioFormDto extends CommonFormDto {
    private UUID id;
    private String title;
    private String url;
    private PortfolioType type;
    private String category;
    private String tag;
    private MultipartFile thumbnail;
    private MultipartFile header;
    private String content;
    private String description;


}
