package com.kks.kksolution.service;

import com.kks.kksolution.dto.common.DeleteFormDto;
import com.kks.kksolution.dto.content.ContentDto;
import com.kks.kksolution.dto.filter.ContentFilterDto;
import com.kks.kksolution.dto.filter.PopUpFilterDto;
import com.kks.kksolution.dto.popup.PopupDto;
import com.kks.kksolution.dto.popup.PopupFormDto;
import com.kks.kksolution.entity.Popup;
import com.kks.kksolution.repository.PopupRepository;
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
public class PopupService {
    private final PopupRepository popupRepository;
    private final HttpServletRequest request;
    private final HttpSession session;
    private final MessageSource messageSource;
    private final LocaleComponent localeComponent;

    public Page<PopupDto> getPageList(PopUpFilterDto filter) {
        Page<Popup> contentPage = popupRepository.findAll(filter.getPageable());
        return contentPage.map(PopupDto::new);
    }

    public PopupDto getEmptyData() {
        return new PopupDto(Popup.builder().id(UUID.randomUUID()).build());
    }

    public PopupDto getData(UUID id) {
        return new PopupDto(getDataById(id));
    }

    public UUID dataProcess(PopupFormDto form) {
        String message = "data.success.update";
        Popup popup = popupRepository.findById(form.getId()).orElse(null);
        if (popup == null) {//create
            popup = Popup.builder().id(form.getId()).createIp(request.getRemoteAddr()).createBy(SecurityUtil.getCurrentUser()).build();
            message = "data.success.create";
        }
        popup.setTitle(form.getTitle());
        popup.setDescription(form.getDescription());
        popup.setStartAt(form.getStartAt());
        popup.setEndAt(form.getEndAt());
        popup.setSequence(form.getSequence());
        popup.setUrl(form.getUrl());
        popup.setTarget(form.getTarget());
        popup.setViewUrl(form.getViewUrl());
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
