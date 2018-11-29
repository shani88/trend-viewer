package com.naver.hackday.trendviewer.restservice.model;

import java.time.LocalDateTime;
import lombok.*;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.Date;

@Entity
@Table(name = "naver_news")
@NoArgsConstructor(access = AccessLevel.PUBLIC)
@Getter(AccessLevel.PUBLIC)
@Setter(AccessLevel.PUBLIC)
@ToString
public class NaverNews {
    @Id
    @GeneratedValue
    @Column(name = "naver_news_id")
    private Long id;
    @Column(nullable = false)
    private String title;
    @Column(nullable = false)
    private String link;
    @Column(nullable = false)
    private String description;
    @Column(nullable = false)
    private LocalDateTime createdTime;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name="keyword_id")
    @JsonIgnore
    private Keyword keyword;

    @Builder
    public NaverNews(String title, String link, String description, LocalDateTime createdTime,
        Keyword keyword) {
        this.title = title;
        this.link = link;
        this.description = description;
        this.createdTime = createdTime;
        this.keyword = keyword;
    }
}
