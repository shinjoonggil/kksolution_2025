package com.kks.kksolution.entity;

import com.kks.kksolution.enumeration.PortfolioType;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Portfolio {
    @Id
    private UUID id;
    private String title;
    private String url;
    @Enumerated(EnumType.STRING)
    private PortfolioType portfolioType;
    private String category;
    private String tag;
    @ManyToOne(fetch = FetchType.LAZY)
    private UploadFile thumbnailFile;
    @ManyToOne(fetch = FetchType.LAZY)
    private UploadFile headerFile;
    @Column(columnDefinition = "MEDIUMTEXT")
    private String content;
    @Column(columnDefinition = "MEDIUMTEXT")
    private String description;

    @ManyToOne(fetch = FetchType.LAZY)
    private User createBy;


    @CreationTimestamp
    private LocalDateTime createAt;
    @UpdateTimestamp
    private LocalDateTime updateAt;
    private String createIp;
    private String updateIp;
}
