package com.naver.hackday.trendviewer.restservice.model;

import com.naver.hackday.trendviewer.restservice.openapi.youtube.model.Youtube;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;


@Entity
@Table(name = "keyword")
@NoArgsConstructor(access = AccessLevel.PUBLIC)
@Getter(AccessLevel.PUBLIC)
@Setter(AccessLevel.PUBLIC)
@ToString
public class Keyword{
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    @Column(name = "keyword_id")
    private Long id;
    @Column(nullable = false)
    private String name;
    @Column(nullable = false, name = "created_time")
    private LocalDateTime createdTime;
    @Column(nullable = false)
    private int rank;

    @OneToMany
    private List<NaverNews> naverNewsList = new ArrayList<>();

    @OneToMany(fetch = FetchType.EAGER)
    private List<Youtube> youtubeList = new ArrayList<>();

    @Builder
    public Keyword(String name, LocalDateTime createdTime, int rank,
        List<NaverNews> naverNewsList,
        List<Youtube> youtubeList) {
        this.name = name;
        this.createdTime = createdTime;
        this.rank = rank;
        this.naverNewsList = naverNewsList;
        this.youtubeList = youtubeList;
    }
}
