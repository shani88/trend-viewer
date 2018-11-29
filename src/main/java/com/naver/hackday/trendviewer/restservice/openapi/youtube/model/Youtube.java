package com.naver.hackday.trendviewer.restservice.openapi.youtube.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Youtube {

  private String title;

  private String thumbnail;

  private String url;

}
