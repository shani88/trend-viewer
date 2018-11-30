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
    //  Tue, 27 Nov 2018 18:35:00 +0900
    String formatedTime = time.substring(5, 24);
    DateTimeFormatter df = DateTimeFormatter.ofPattern("dd MMM yyyy HH:mm:ss");
    return LocalDateTime.parse(formatedTime, df);

    //    return LocalDateTime.parse(time);
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
