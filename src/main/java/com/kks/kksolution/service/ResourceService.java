package com.kks.kksolution.service;

import com.kks.kksolution.dto.common.DeleteFormDto;
import com.kks.kksolution.dto.filter.ResourceFilterDto;
import com.kks.kksolution.dto.resource.ResourceDto;
import com.kks.kksolution.dto.resource.ResourceFormDto;
import com.kks.kksolution.entity.Resource;
import com.kks.kksolution.repository.ResourceGroupRepository;
import com.kks.kksolution.repository.ResourceRepository;
import com.kks.kksolution.util.LocaleComponent;
import com.kks.kksolution.vo.common.MessageVO;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.MessageSource;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ResourceService implements CommonServiceImpl<Resource, ResourceDto, ResourceFormDto, ResourceFilterDto> {
    private final ResourceGroupRepository resourceGroupRepository;
    private final ResourceRepository resourceRepository;
    private final MessageSource messageSource;
    private final LocaleComponent localeComponent;
    @Value("${cloud.aws.s3.endpoint}")
    private String endPoint;
    @Value("${cloud.aws.s3.bucket}")
    private String bucket;
    private final HttpServletRequest request;
    private final HttpSession session;

    public Page<ResourceDto> getPageList(ResourceFilterDto filter) {
        Page<Resource> page = resourceRepository.findAll(filter.getPageable());
        return page.map(content -> new ResourceDto(content, endPoint, bucket));
    }

    public ResourceDto getEmptyData() {
        return new ResourceDto(Resource.builder().id(UUID.randomUUID()).build(), endPoint, bucket);
    }

    public ResourceDto getData(UUID id) {
        return new ResourceDto(getDataById(id), endPoint, bucket);
    }

    @Override
    public UUID dataProcess(ResourceFormDto resourceFormDto) {
        return null;
    }

    @Override
    public void deleteData(DeleteFormDto form) {

    }

    public void deleteProcess(DeleteFormDto form) {
        String deleteText = form.getDeleteText().trim();
        String originDeleteText = messageSource.getMessage("admin.delete.keyword", null, localeComponent.getLocale());
        if (!deleteText.equals(originDeleteText)) {
            session.setAttribute("message", MessageVO.ERROR("error.keyword"));
            return;
        }
        resourceRepository.deleteById(form.getId());
        session.setAttribute("message", MessageVO.SUCCESS("data.success.delete"));

    }

    private Resource getDataById(UUID id) {
        return resourceRepository.findById(id).orElseThrow(() -> new RuntimeException("data.error.null"));
    }
}
