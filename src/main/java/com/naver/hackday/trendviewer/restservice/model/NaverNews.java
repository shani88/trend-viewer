package com.naver.hackday.trendviewer.restservice.model;

import java.time.LocalDateTime;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "naver_news")
@NoArgsConstructor(access = AccessLevel.PUBLIC)
@Getter(AccessLevel.PUBLIC)
@Setter(AccessLevel.PUBLIC)
@ToString
public class NaverNews {
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
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

    @Builder
    public NaverNews(String title, String link, String description, LocalDateTime createdTime) {
        this.title = title;
        this.link = link;
        this.description = description;
        this.createdTime = createdTime;
    }
}
