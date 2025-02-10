package com.kks.kksolution.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;

@RestController
@RequestMapping("upload")
public class UploadController {
    @Value("${java.io.tmpdir}")
    private String tempDir;

    @PostMapping("{index}")
    public String upload(@PathVariable Object index, @RequestParam("file") MultipartFile file) throws IOException {
        if(file.isEmpty()){
            return "파일이 비었습니다";
        }
        String originalFilename = StringUtils.cleanPath(file.getOriginalFilename());
        Path tempFilePath = new File(tempDir, originalFilename).toPath();
        try {
            Files.copy(file.getInputStream(), tempFilePath, StandardCopyOption.REPLACE_EXISTING);
        } catch (IOException e) {
            throw new IOException("파일 업로드 실패", e);
        }
        return tempFilePath.toString();
    }
}
