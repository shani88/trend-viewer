package com.naver.hackday.trendviewer.restservice.openapi.naver.model;

import com.fasterxml.jackson.annotation.JsonProperty;
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

}
