package com.kks.kksolution.dto.upload;

import com.kks.kksolution.entity.UploadFile;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;

import java.util.UUID;

@Getter
public class UploadFileDto {

    private UUID id;
    private UUID groupId;
    private String origin;
    private String type;
    private Long size;
    private String url;


    public UploadFileDto(UploadFile uploadFile,String endPoint,String bucket) {
        this.id = uploadFile.getId();
        this.groupId = uploadFile.getGroupId();
        this.origin = uploadFile.getOrigin();
        this.type= uploadFile.getContentType();
        this.size = uploadFile.getSize();
        this.url = String.format("%s/%s/%s/%s" , endPoint,bucket, uploadFile.getGroupId(), uploadFile.getId());
    }
}


