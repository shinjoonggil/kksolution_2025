package com.kks.kksolution.dto.portfolio;

import com.kks.kksolution.dto.upload.UploadFileDto;
import com.kks.kksolution.entity.Portfolio;
import com.kks.kksolution.entity.User;
import com.kks.kksolution.constant.PortfolioType;
import lombok.Getter;

import java.util.UUID;

@Getter
public class PortfolioDto {
    private UUID id;
    private String title;
    private String url;
    private PortfolioType type;
    private String category;
    private String tag;
    private UploadFileDto thumbnail;
    private UploadFileDto header;
    private String content;
    private String description;
    private User createBy;

    public PortfolioDto(Portfolio portfolio, String endPoint, String bucket) {
        this.id = portfolio.getId();
        this.title = portfolio.getTitle();
        this.url = portfolio.getUrl();
        this.type = portfolio.getPortfolioType();
        this.category = portfolio.getCategory();
        this.tag = portfolio.getTag();
        if (portfolio.getThumbnailFile() != null) {
            this.thumbnail = new UploadFileDto(portfolio.getThumbnailFile(), endPoint, bucket);
        }
        if (portfolio.getHeaderFile() != null) {
            this.header = new UploadFileDto(portfolio.getHeaderFile(), endPoint, bucket);
        }
        this.createBy = portfolio.getCreateBy();
        this.content = portfolio.getContent();
        this.description = portfolio.getDescription();


    }
}
