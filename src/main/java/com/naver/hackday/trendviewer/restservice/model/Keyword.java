package com.naver.hackday.trendviewer.restservice.model;

import lombok.*;

import javax.persistence.*;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;


@Entity
@Table(name = "keyword")
@NoArgsConstructor(access = AccessLevel.PUBLIC)
@Getter(AccessLevel.PUBLIC)
@Setter(AccessLevel.PUBLIC)
@ToString
class Keyword{
    @Id
    @GeneratedValue
    @Column(name = "keyword_id")
    private Long id;
    @Column(nullable = false)
    private String name;
    @Column(nullable = false)
    private Date created_time;
    @Column(nullable = false)
    private int rank;

    @OneToMany(mappedBy="keyword", cascade=CascadeType.ALL)
    private Set<NaverNews> naverNewsSet = new HashSet<>();
}
