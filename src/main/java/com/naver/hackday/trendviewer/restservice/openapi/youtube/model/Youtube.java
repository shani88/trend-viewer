package com.naver.hackday.trendviewer.restservice.openapi.youtube.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.naver.hackday.trendviewer.restservice.model.Keyword;
import lombok.*;

import javax.persistence.*;


@Entity
@Getter(AccessLevel.PUBLIC)
@Setter(AccessLevel.PUBLIC)
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Youtube {
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    @Column(name = "youtube_id")
    private Long id;
    @Column(nullable = false)
    private String title;
    @Column(nullable = false)
    private String thumbnail;
    @Column(nullable = false)
    private String url;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name="keyword_id")
    @JsonIgnore
    private Keyword keyword;
}
