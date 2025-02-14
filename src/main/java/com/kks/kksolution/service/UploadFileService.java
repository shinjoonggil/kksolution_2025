package com.kks.kksolution.service;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.*;
import com.kks.kksolution.dto.upload.UploadFileDto;
import com.kks.kksolution.entity.UploadFile;

import com.kks.kksolution.repository.UploadFileRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Slf4j
public class UploadFileService {
    @Value("${cloud.aws.s3.bucket}")
    private String bucket;
    @Value("${cloud.aws.s3.endpoint}")
    private String endPoint;
    private final AmazonS3 amazonS3;
    private final UploadFileRepository uploadFileRepository;


    public UploadFile upload(MultipartFile multipartFile, UUID groupIndex) throws IOException {
        UUID fileIndex = UUID.randomUUID();
        ObjectMetadata objectMetadata = new ObjectMetadata();
        objectMetadata.setContentType(multipartFile.getContentType());
        objectMetadata.setContentLength(multipartFile.getSize());
        InputStream inputStream = multipartFile.getInputStream();
        String saveKey = String.format("%s/%s", groupIndex, fileIndex);

        PutObjectRequest putObjectRequest =
                new PutObjectRequest(
                        bucket,
                        saveKey,
                        inputStream,
                        objectMetadata
                ).withCannedAcl(
                        CannedAccessControlList.PublicRead
                );

        PutObjectResult putObjectResult = amazonS3.putObject(putObjectRequest);

        return uploadFileRepository.save(UploadFile.builder()
                .id(fileIndex)
                .groupId(groupIndex)
                .origin(multipartFile.getOriginalFilename())
                .contentType(multipartFile.getContentType())
                .size(multipartFile.getSize())
                .build());
    }

    public List<UploadFileDto> getUploadFiles(UUID groupId) {
        return uploadFileRepository.findAllByGroupId(groupId).stream().map(uploadFile -> new UploadFileDto(uploadFile, endPoint, bucket)).toList();
    }

    public void deleteUploadFile(UUID groupId, UUID id) {
        uploadFileRepository.findByGroupIdAndId(groupId, id).ifPresent(this::delete);
    }

    public void deleteUploadFile(UploadFile uploadFile) {
        uploadFileRepository.findByGroupIdAndId(uploadFile.getGroupId(), uploadFile.getId()).ifPresent(this::delete);
    }

    public void deleteGroupUploadFile(UUID groupId) {
        List<UploadFile> uploadFiles = uploadFileRepository.findAllByGroupId(groupId);
        uploadFiles.forEach(uploadFile -> {
            deleteUploadFile(uploadFile.getGroupId(), uploadFile.getId());
        });
    }

    private void delete(UploadFile uploadFile) {
        String key = String.format("%s/%s", uploadFile.getGroupId(), uploadFile.getOrigin());
        try {
            amazonS3.deleteObject(new DeleteObjectRequest(bucket, key));
            uploadFileRepository.deleteById(uploadFile.getId());
        } catch (Exception e) {
            log.error(e.getLocalizedMessage());
            log.error("key : {} ", key);
        }
    }

    public void uploadFiles(UUID groupId, List<MultipartFile> uploadFiles) {
        List<MultipartFile> exceptionFiles = new ArrayList<>();
        for (MultipartFile uploadFile : uploadFiles) {
            try {
                UploadFile uploadResult = upload(uploadFile, groupId);
            } catch (IOException e) {
                exceptionFiles.add(uploadFile);
            }
            log.info("exceptionFiles : {} ", exceptionFiles.size());
        }
    }

    public Resource getFileResource(UUID groupId, UUID id) {

        S3Object object = amazonS3.getObject(bucket, String.format("%s/%s", groupId, id));

        S3ObjectInputStream objectInputStream = object.getObjectContent();
        return new InputStreamResource(objectInputStream);
    }
}
