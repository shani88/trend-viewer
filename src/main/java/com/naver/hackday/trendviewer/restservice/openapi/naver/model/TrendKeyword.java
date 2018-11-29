package com.naver.hackday.trendviewer.restservice.openapi.naver.model;

import com.naver.hackday.trendviewer.restservice.model.Keyword;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TrendKeyword {

  private String createdTime;

  private List<KeywordModel> keywordList;

  public List<Keyword> toEntity() {
    return this.keywordList.stream().map(keyword -> {
      return new Keyword().builder()
          .name(keyword.getKeyword())
          .rank(keyword.getRank())
          .createdTime(LocalDateTime.parse(this.createdTime))
          .build();
    }).collect(Collectors.toList());
  }
}
