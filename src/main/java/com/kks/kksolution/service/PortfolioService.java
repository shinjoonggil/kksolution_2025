package com.kks.kksolution.service;

import com.kks.kksolution.dto.common.DeleteFormDto;
import com.kks.kksolution.dto.filter.PortfolioFilterDto;


import com.kks.kksolution.dto.portfolio.PortfolioDto;
import com.kks.kksolution.dto.portfolio.PortfolioFormDto;
import com.kks.kksolution.entity.Portfolio;
import com.kks.kksolution.entity.UploadFile;
import com.kks.kksolution.repository.PortfolioRepository;
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

import java.util.UUID;

@Service
@RequiredArgsConstructor
@Slf4j
public class PortfolioService  {
    private final PortfolioRepository portfolioRepository;
    private final HttpServletRequest request;
    private final HttpSession session;
    private final MessageSource messageSource;
    private final LocaleComponent localeComponent;
    private final UploadFileService uploadFileService;

    @Value("${cloud.aws.s3.endpoint}")
    private String endPoint;
    @Value("${cloud.aws.s3.bucket}")
    private String bucket;

    public Page<PortfolioDto> getPageList(PortfolioFilterDto filter) {
        Page<Portfolio> contentPage = portfolioRepository.findAll(filter.getPageable());
        return contentPage.map(Portfolio -> new PortfolioDto(Portfolio, endPoint, bucket));
    }

    public PortfolioDto getEmptyData() {
        return new PortfolioDto(Portfolio.builder().id(UUID.randomUUID()).build(), endPoint, bucket);
    }

    public PortfolioDto getData(UUID id) {
        return new PortfolioDto(getDataById(id), endPoint, bucket);
    }

    public UUID dataProcess(PortfolioFormDto form) {
        String message = "data.success.update";
        Portfolio portfolio = portfolioRepository.findById(form.getId()).orElse(null);
        if (portfolio == null) {//create
            portfolio = Portfolio.builder()
                    .id(form.getId())
                    .createIp(request.getRemoteAddr())
                    .createBy(SecurityUtil.getCurrentUser())
                    .build();
            message = "data.success.create";
        }

        if (!form.getThumbnail().isEmpty()) {
            log.info("thumbnail : {}", form.getThumbnail());
            try {
                UploadFile uploadFile = uploadFileService.upload(form.getThumbnail(), portfolio.getId());
                portfolio.setThumbnailFile(uploadFile);
            } catch (Exception e) {
                log.info("thumbnail upload error");
            }
        }
        if (!form.getHeader().isEmpty()) {
            log.info("header : {}", form.getHeader());
            try {
                UploadFile uploadFile = uploadFileService.upload(form.getHeader(), portfolio.getId());
                portfolio.setHeaderFile(uploadFile);
            } catch (Exception e) {
                log.info("thumbnail upload error");
            }
        }

        portfolio.setTitle(form.getTitle());
        portfolio.setUrl(form.getUrl());
        portfolio.setPortfolioType(form.getType());
        portfolio.setCategory(form.getCategory());
        portfolio.setTag(form.getTag());
        portfolio.setContent(form.getContent());
        portfolio.setDescription(form.getDescription());

        portfolio = portfolioRepository.save(portfolio);
        session.setAttribute("message", MessageVO.SUCCESS(message));
        return portfolio.getId();
    }

    public void deleteProcess(DeleteFormDto form) {
        String deleteText = form.getDeleteText().trim();
        String originDeleteText = messageSource.getMessage("admin.delete.keyword", null, localeComponent.getLocale());
        if (!deleteText.equals(originDeleteText)) {
            session.setAttribute("message", MessageVO.ERROR("error.keyword"));
            return;
        }
        portfolioRepository.deleteById(form.getId());
        session.setAttribute("message", MessageVO.SUCCESS("data.success.delete"));

    }

    private Portfolio getDataById(UUID id) {
        return portfolioRepository.findById(id).orElseThrow(() -> new RuntimeException("data.error.null"));
    }
}
