package com.kks.kksolution.service;

import com.kks.kksolution.dto.common.DeleteFormDto;
import com.kks.kksolution.dto.filter.ResourceFilterDto;
import com.kks.kksolution.dto.resource.ResourceDto;
import com.kks.kksolution.dto.resource.ResourceFormDto;
import com.kks.kksolution.dto.resource.ResourceGroupDto;
import com.kks.kksolution.entity.Resource;
import com.kks.kksolution.entity.ResourceGroup;
import com.kks.kksolution.entity.UploadFile;
import com.kks.kksolution.repository.ResourceGroupRepository;
import com.kks.kksolution.repository.ResourceRepository;
import com.kks.kksolution.util.LocaleComponent;
import com.kks.kksolution.util.SecurityUtil;
import com.kks.kksolution.vo.common.MessageVO;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.MessageSource;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Slf4j
public class ResourceService implements CommonServiceImpl<Resource, ResourceDto, ResourceFormDto, ResourceFilterDto> {
    private final ResourceGroupRepository resourceGroupRepository;
    private final ResourceRepository resourceRepository;
    private final UploadFileService uploadFileService;
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
    public UUID dataProcess(ResourceFormDto form) {
        log.info(form.toString());
        String message = "data.success.update";
        Resource resource = resourceRepository.findById(form.getId()).orElse(null);
        if (resource == null) {
            resource = Resource.builder()
                    .id(form.getId())
                    .createIp(request.getRemoteAddr())
                    .createBy(SecurityUtil.getCurrentUser())
                    .build();
            message = "data.success.create";
        }
        if (!form.getResource().isEmpty()) {
            try {
                UploadFile uploadFile = uploadFileService.upload(form.getResource(), resource.getId());
                resource.setResource(uploadFile);
            } catch (IOException e) {
                log.info(e.getMessage());
            }
        }


        ResourceGroup group = resourceGroupRepository.findByTitle(form.getGroupTitle().trim()).orElseGet(()->
                resourceGroupRepository.save(ResourceGroup.builder()
                        .id(UUID.randomUUID())
                        .title(form.getGroupTitle().trim())
                        .createBy(SecurityUtil.getCurrentUser())
                        .createIp(request.getRemoteAddr())
                        .updateIp(request.getRemoteAddr())
                        .build())
        );

        resource.setTitle(form.getTitle());
        resource.setGroup(group);
        resource.setUpdateIp(resource.getUpdateIp());
        resource=resourceRepository.save(resource);
        session.setAttribute("message", MessageVO.SUCCESS(message));
        return resource.getId();
    }

    @Override
    public void deleteData(DeleteFormDto form) {
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

    public List<ResourceGroupDto> getAllGroup() {
        return resourceGroupRepository.findAll().stream().map(ResourceGroupDto::new).toList();
    }
}
