package com.kks.kksolution.service;

import com.kks.kksolution.dto.common.DeleteFormDto;
import com.kks.kksolution.dto.filter.InquiryFilterDto;
import com.kks.kksolution.dto.inquiry.InquiryDto;
import com.kks.kksolution.dto.inquiry.InquiryDto;
import com.kks.kksolution.dto.inquiry.InquiryFormDto;
import com.kks.kksolution.entity.Inquiry;
import com.kks.kksolution.entity.UploadFile;
import com.kks.kksolution.repository.InquiryRepository;
import com.kks.kksolution.repository.InquiryRepository;
import com.kks.kksolution.util.LocaleComponent;
import com.kks.kksolution.util.SecurityUtil;
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
public class InquiryService {
    private final InquiryRepository inquiryRepository;
    private final HttpServletRequest request;
    private final HttpSession session;
    private final MessageSource messageSource;
    private final LocaleComponent localeComponent;
    private final UploadFileService uploadFileService;

    @Value("${cloud.aws.s3.endpoint}")
    private String endPoint;
    @Value("${cloud.aws.s3.bucket}")
    private String bucket;

    public Page<InquiryDto> getPageList(InquiryFilterDto filter) {
        Page<Inquiry> contentPage = inquiryRepository.findAll(filter.getPageable());
        return contentPage.map(Inquiry -> new InquiryDto(Inquiry));
    }

    public InquiryDto getEmptyData() {
        return new InquiryDto(Inquiry.builder().id(UUID.randomUUID()).build());
    }

    public InquiryDto getData(UUID id) {
        return new InquiryDto(getDataById(id));
    }

    public UUID dataProcess(InquiryFormDto form) {
        String message = "data.success.update";
        Inquiry inquiry = inquiryRepository.findById(form.getId()).orElse(null);
        if (inquiry == null) {//create
            inquiry = Inquiry.builder()
                    .id(form.getId())
                    .createIp(request.getRemoteAddr())
                    .build();
            message = "data.success.create";
        }




        inquiry.setCompanyName(form.getTitle());
        inquiry.setUrl(form.getUrl());
        inquiry.setInquiryType(form.getType());
        inquiry.setContent(form.getContent());
        inquiry.setDescription(form.getDescription());

        inquiry = inquiryRepository.save(inquiry);
        session.setAttribute("message", MessageVO.SUCCESS(message));
        return inquiry.getId();
    }

    public void deleteProcess(DeleteFormDto form) {
        String deleteText = form.getDeleteText().trim();
        String originDeleteText = messageSource.getMessage("admin.delete.keyword", null, localeComponent.getLocale());
        if (!deleteText.equals(originDeleteText)) {
            session.setAttribute("message", MessageVO.ERROR("error.keyword"));
            return;
        }
        inquiryRepository.deleteById(form.getId());
        session.setAttribute("message", MessageVO.SUCCESS("data.success.delete"));

    }
    public String writeQuickInquiry(){

        return "";
    }

    private Inquiry getDataById(UUID id) {
        return inquiryRepository.findById(id).orElseThrow(() -> new RuntimeException("data.error.null"));
    }
}
