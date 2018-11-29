package com.naver.hackday.trendviewer.restservice.openapi.naver.model;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class TrendKeyword {

  private String createdTime;

  private List<String> keywordList;

}
