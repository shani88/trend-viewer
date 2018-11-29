package com.naver.hackday.trendviewer.restservice.model;

import lombok.*;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "naver_news")
@NoArgsConstructor(access = AccessLevel.PUBLIC)
@Getter(AccessLevel.PUBLIC)
@Setter(AccessLevel.PUBLIC)
@ToString
class NaverNews {
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
    private Date created_time;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name="keyword_id")
    private Keyword keyword;
}
