package com.naver.hackday.trendviewer.restservice.openapi.naver.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.naver.hackday.trendviewer.restservice.model.Keyword;
import com.naver.hackday.trendviewer.restservice.model.NaverNews;
import java.time.LocalDateTime;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class NaverNewsItems {

  @JsonProperty("title")
  private String title;

  @JsonProperty("originallink")
  private String originalLink;

  @JsonProperty("link")
  private String link;

  @JsonProperty("description")
  private String description;

  @JsonProperty("pubDate")
  private String pubDate;

//  public NaverNews toEntity(Keyword keyword) {
//    NaverNews naverNews = new NaverNews().builder()
//        .title(title)
//        .link(link)
//        .description(description)
//        .keyword(keyword);
//        .(LocalDateTime.parse(pubDate))
//
//  }
}
