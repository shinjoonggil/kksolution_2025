package com.kks.kksolution.controller;

import com.kks.kksolution.dto.upload.UploadFileDto;
import com.kks.kksolution.repository.UploadFileRepository;
import com.kks.kksolution.service.UploadFileService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("upload")
@RequiredArgsConstructor
public class UploadController {
    private final UploadFileService uploadFileService;
    private static final Logger log = LoggerFactory.getLogger(UploadController.class);
    @Value("${java.io.tmpdir}")
    private String tempDir;


    private final UploadFileRepository uploadFileRepository;

    @PostMapping(value = "{groupId}", consumes = "multipart/form-data", produces = "application/json")
    public ResponseEntity<List<UploadFileDto>> upload(@PathVariable UUID groupId, @RequestParam("uploadFiles") List<MultipartFile> uploadFiles){
        uploadFileService.uploadFiles(groupId, uploadFiles);
        return ResponseEntity.ok(uploadFileService.getUploadFiles(groupId));
    }

    @GetMapping("{groupId}")
    public ResponseEntity<List<UploadFileDto>> getFileGroup(@PathVariable UUID groupId) {
        return ResponseEntity.ok(uploadFileService.getUploadFiles(groupId));
    }

    @DeleteMapping("{groupId}/{id}")
    public ResponseEntity<List<UploadFileDto>> deleteFile(@PathVariable UUID groupId, @PathVariable UUID id) {
        uploadFileService.deleteUploadFile(groupId,id);
        return ResponseEntity.ok(uploadFileService.getUploadFiles(groupId));
    }
    @DeleteMapping("{groupId}")
    public ResponseEntity<List<UploadFileDto>> deleteFile(@PathVariable UUID groupId) {
        uploadFileService.deleteGroupUploadFile(groupId);
        return ResponseEntity.ok(uploadFileService.getUploadFiles(groupId));
    }
    @GetMapping("{groupId}/{id}")
    public ResponseEntity<Resource> downLoadFile(@PathVariable UUID groupId, @PathVariable UUID id) {
        return ResponseEntity.ok(uploadFileService.getFileResource(groupId,id));
    }
}
