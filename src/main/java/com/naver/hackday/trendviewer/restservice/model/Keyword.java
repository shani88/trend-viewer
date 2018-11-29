package com.naver.hackday.trendviewer.restservice.model;

import lombok.*;

import javax.persistence.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;


@Entity
@Table(name = "keyword")
@NoArgsConstructor(access = AccessLevel.PUBLIC)
@Getter(AccessLevel.PUBLIC)
@Setter(AccessLevel.PUBLIC)
@ToString
public class Keyword{
    @Id
    @GeneratedValue
    @Column(name = "keyword_id")
    private Long id;
    @Column(nullable = false)
    private String name;
    @Column(nullable = false, name = "created_time")
    private LocalDateTime createdTime;
    @Column(nullable = false)
    private int rank;

    @OneToMany(mappedBy="keyword", cascade=CascadeType.ALL)
    private List<NaverNews> naverNewsSet = new ArrayList<>();

    @Builder
    public Keyword(String name, LocalDateTime createdTime, int rank,
        List<NaverNews> naverNewsSet) {
        this.name = name;
        this.createdTime = createdTime;
        this.rank = rank;
        this.naverNewsSet = naverNewsSet;
    }
}
