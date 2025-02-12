package com.kks.kksolution.service;

import com.kks.kksolution.dto.common.DeleteFormDto;
import com.kks.kksolution.dto.content.ContentDto;
import com.kks.kksolution.dto.content.ContentFormDto;
import com.kks.kksolution.dto.filter.ContentFilterDto;
import com.kks.kksolution.entity.Content;
import com.kks.kksolution.repository.ContentRepository;
import com.kks.kksolution.util.LocaleComponent;
import com.kks.kksolution.util.SecurityUtil;
import com.kks.kksolution.vo.common.MessageVO;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.context.MessageSource;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.Locale;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ContentService {
    private final ContentRepository contentRepository;
    private final HttpServletRequest request;
    private final HttpSession session;
    private final MessageSource messageSource;
    private final LocaleComponent localeComponent;


    public Page<ContentDto> getContents(ContentFilterDto filter) {
        Page<Content> contentPage = contentRepository.findAll(filter.getPageable());
        return contentPage.map(ContentDto::new);
    }

    public ContentDto getEmptyContent() {
        return new ContentDto(Content.builder().id(UUID.randomUUID()).build());
    }

    public ContentDto getContent(UUID id) {
        return new ContentDto(getContentById(id));
    }

    public UUID contentProcess(ContentFormDto form) {
        String message = "content.success.update";
        Content content = contentRepository.findById(form.getId()).orElse(null);

        if (content == null) {//create
            content = Content.builder().id(form.getId()).createIp(request.getRemoteAddr()).createBy(SecurityUtil.getCurrentUser()).build();
            message = "content.success.create";

        }
        content.setTitle(form.getTitle());
        content.setPath(form.getPath());
        content.setContent(form.getContent());
        content.setUpdateIp(request.getRemoteAddr());

        content = contentRepository.save(content);
        session.setAttribute("message", MessageVO.SUCCESS(message));
        return content.getId();
    }



    public void deleteProcess(DeleteFormDto form) {
        String deleteText = form.getDeleteText().trim();
        String originDeleteText = messageSource.getMessage("admin.delete.keyword", null, localeComponent.getLocale());
        if (!deleteText.equals(originDeleteText)) {
            session.setAttribute("message", MessageVO.ERROR("error.keyword"));
            return;
        }
        contentRepository.deleteById(form.getId());
        session.setAttribute("message", MessageVO.SUCCESS("data.success.delete"));

    }
    private Content getContentById(UUID id) {
        return contentRepository.findById(id).orElseThrow(() -> new RuntimeException("content.error.null"));
    }
}
