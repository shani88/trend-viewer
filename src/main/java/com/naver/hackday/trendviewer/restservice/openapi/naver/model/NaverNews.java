package com.naver.hackday.trendviewer.restservice.openapi.naver.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class NaverNews {

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
}
