package com.kks.kksolution.dto.upload;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.web.multipart.MultipartFile;
import java.util.List;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class UploadFormDto {
    private List<MultipartFile> uploadFiles;
}
