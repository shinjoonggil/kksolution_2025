package com.kks.kksolution.dto.resource;

import com.kks.kksolution.entity.ResourceGroup;
import lombok.Getter;

@Getter
public class ResourceGroupDto {
    private String title;
    public ResourceGroupDto(ResourceGroup group){
        this.title=group.getTitle();
    }
}
