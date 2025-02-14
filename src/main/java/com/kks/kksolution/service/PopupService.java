package com.kks.kksolution.service;

import com.kks.kksolution.dto.common.DeleteFormDto;
import com.kks.kksolution.dto.filter.PopUpFilterDto;
import com.kks.kksolution.dto.popup.PopupDto;
import com.kks.kksolution.dto.popup.PopupFormDto;
import com.kks.kksolution.entity.Popup;
import com.kks.kksolution.entity.UploadFile;
import com.kks.kksolution.repository.PopupRepository;
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
import java.time.LocalDateTime;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Slf4j
public class PopupService {
    private final PopupRepository popupRepository;
    private final HttpServletRequest request;
    private final HttpSession session;
    private final MessageSource messageSource;
    private final LocaleComponent localeComponent;
    private final UploadFileService uploadFileService;

    @Value("${cloud.aws.s3.endpoint}")
    private String endPoint;
    @Value("${cloud.aws.s3.bucket}")
    private String bucket;

    public Page<PopupDto> getPageList(PopUpFilterDto filter) {
        Page<Popup> contentPage = popupRepository.findAll(filter.getPageable());
        return contentPage.map(popup -> new PopupDto(popup, endPoint, bucket));
    }

    public PopupDto getEmptyData() {
        return new PopupDto(Popup.builder().id(UUID.randomUUID()).startAt(LocalDateTime.now()).endAt(LocalDateTime.now().plusWeeks(1)).build(), endPoint, bucket);
    }

    public PopupDto getData(UUID id) {
        return new PopupDto(getDataById(id), endPoint, bucket);
    }

    public UUID dataProcess(PopupFormDto form) {
        String message = "data.success.update";
        Popup popup = popupRepository.findById(form.getId()).orElse(null);
        log.info("USer : {}" , SecurityUtil.getCurrentUser());
        if (popup == null) {//create
            popup = Popup.builder()
                    .id(form.getId())
                    .createIp(request.getRemoteAddr())
                    .createBy(SecurityUtil.getCurrentUser())
                    .build();
            message = "data.success.create";
        }

        UploadFile uploadFile = popup.getUploadFile();
        if (form.getDeleteFile()) {
            uploadFile = null;
            uploadFileService.deleteUploadFile(popup.getUploadFile());
        } else {
            if (!form.getUploadFile().isEmpty()) {
                try {
                    uploadFile = uploadFileService.upload(form.getUploadFile(), popup.getId());
                    if (popup.getUploadFile() != null) uploadFileService.deleteUploadFile(popup.getUploadFile());
                } catch (IOException e) {
                    session.setAttribute("message", "error.upload");
                }
            }
        }


        popup.setTitle(form.getTitle());
        popup.setDescription(form.getDescription());
        popup.setStartAt(form.getStartAt());
        popup.setEndAt(form.getEndAt());
        popup.setSequence(form.getSequence());
        popup.setUrl(form.getUrl());
        popup.setTarget(form.getTarget());
        popup.setAlt(form.getAlt());
        popup.setUploadFile(uploadFile);
        popup.setUpdateIp(request.getRemoteAddr());
        popup = popupRepository.save(popup);
        session.setAttribute("message", MessageVO.SUCCESS(message));
        return popup.getId();
    }

    public void deleteProcess(DeleteFormDto form) {
        String deleteText = form.getDeleteText().trim();
        String originDeleteText = messageSource.getMessage("admin.delete.keyword", null, localeComponent.getLocale());
        if (!deleteText.equals(originDeleteText)) {
            session.setAttribute("message", MessageVO.ERROR("error.keyword"));
            return;
        }
        popupRepository.deleteById(form.getId());
        session.setAttribute("message", MessageVO.SUCCESS("data.success.delete"));

    }

    private Popup getDataById(UUID id) {
        return popupRepository.findById(id).orElseThrow(() -> new RuntimeException("data.error.null"));
    }
}
