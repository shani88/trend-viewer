package com.naver.hackday.trendviewer.restservice.openapi.youtube.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;


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

}
