package com.kks.kksolution.dto.resource;

import com.kks.kksolution.dto.upload.UploadFileDto;
import com.kks.kksolution.entity.Resource;
import com.kks.kksolution.entity.ResourceGroup;
import com.kks.kksolution.entity.User;
import lombok.Getter;

import java.util.UUID;

@Getter
public class ResourceDto {
    private UUID id;
    private ResourceGroup group;
    private UploadFileDto resource;
    private String title;
    private User createBy;
    public ResourceDto(Resource resource, String endPoint, String bucket) {
        this.id=resource.getId();
        this.group = resource.getGroup();
        this.title = resource.getTitle();
        this.createBy=resource.getCreateBy();
        if (resource.getResource() != null) this.resource = new UploadFileDto(resource.getResource(), endPoint, bucket);

    }
}
