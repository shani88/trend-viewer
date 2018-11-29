package com.naver.hackday.trendviewer.restservice.model;

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
    private Date created_time;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name="keyword_id")
    @JsonIgnore
    private Keyword keyword;
}
