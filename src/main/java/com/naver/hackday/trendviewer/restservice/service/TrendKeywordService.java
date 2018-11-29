package com.naver.hackday.trendviewer.restservice.service;

import com.naver.hackday.trendviewer.restservice.model.Keyword;
import com.naver.hackday.trendviewer.restservice.openapi.exception.NoContentsException;
import com.naver.hackday.trendviewer.restservice.openapi.naver.NaverNewsAPI;
import com.naver.hackday.trendviewer.restservice.openapi.naver.TrendKeywordAPI;
import com.naver.hackday.trendviewer.restservice.openapi.naver.model.TrendKeyword;
import com.naver.hackday.trendviewer.restservice.openapi.youtube.YoutubeAPI;
import com.naver.hackday.trendviewer.restservice.repository.KeywordRepository;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class TrendKeywordService {

  private static final Logger logger = LoggerFactory.getLogger(TrendKeywordService.class);

  @Autowired
  private NaverNewsAPI naverNewsAPI;

  @Autowired
  private YoutubeAPI youtubeAPI;

  @Autowired
  private TrendKeywordAPI trendKeywordAPI;

  @Autowired
  private KeywordRepository keywordRepository;

  @Transactional
  public void collectData() {
    List<TrendKeyword> trendKeywords = trendKeywordAPI.request();

    saveTrendKeyword();
  }

  protected void saveTrendKeyword() {
    List<TrendKeyword> trendKeywords = trendKeywordAPI.request();
    if (trendKeywords.size() == 0)
      throw new NoContentsException("no contents");

    for (TrendKeyword keyword : trendKeywords) {
      List<Keyword> keywordList = keyword.toEntity();
      for (Keyword key : keywordList)
        keywordRepository.save(key);
    }
  }
}
