package com.naver.hackday.trendviewer.restservice.openapi.naver.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.naver.hackday.trendviewer.restservice.model.NaverNews;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class NaverNewsModel {

  @JsonProperty("lastBuildDate")
  private String lastBuildDate;

  @JsonProperty("total")
  private int total;

  @JsonProperty("start")
  private int start;

  @JsonProperty("display")
  private int display;

  @JsonProperty("items")
  private List<NaverNewsItems> items;

  LocalDateTime changeDateTimeFormat(String time) {
    return LocalDateTime.parse(time, DateTimeFormatter.RFC_1123_DATE_TIME);
  }

  public List<NaverNews> toEntity() {
    return this.items.stream().map(naverNews -> {
      return new NaverNews().builder()
          .title(naverNews.getTitle())
          .link(naverNews.getLink())
          .description(naverNews.getDescription())
          .createdTime(changeDateTimeFormat(naverNews.getPubDate()))
          .build();
    }).collect(Collectors.toList());
  }
}
