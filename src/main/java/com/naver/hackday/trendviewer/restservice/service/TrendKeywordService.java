package com.naver.hackday.trendviewer.restservice.service;

import com.naver.hackday.trendviewer.restservice.model.Keyword;
import com.naver.hackday.trendviewer.restservice.model.NaverNews;
import com.naver.hackday.trendviewer.restservice.openapi.exception.NoContentsException;
import com.naver.hackday.trendviewer.restservice.openapi.naver.NaverNewsAPI;
import com.naver.hackday.trendviewer.restservice.openapi.naver.TrendKeywordAPI;
import com.naver.hackday.trendviewer.restservice.openapi.naver.model.NaverNewsModel;
import com.naver.hackday.trendviewer.restservice.openapi.naver.model.TrendKeyword;
import com.naver.hackday.trendviewer.restservice.openapi.youtube.YoutubeAPI;
import com.naver.hackday.trendviewer.restservice.openapi.youtube.model.Youtube;
import com.naver.hackday.trendviewer.restservice.repository.KeywordRepository;
import java.util.ArrayList;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class TrendKeywordService {

  private static final Logger logger = LoggerFactory.getLogger(TrendKeywordService.class);

  private static final String NEWS_SORTING = "date";

  @Value("${api.display}")
  private int DISPLAY = 5;

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
	  List<Keyword> keywords = loadKeyword();
//    saveNewsData(keywords);

	  for (Keyword keyword : keywords) {
	    List<Youtube> youtubeList = loadYoutube(keyword.getName());

	    keyword.setYoutubeList(youtubeList);
	    keywordRepository.save(keyword);
    }

  }

  private List<Keyword> loadKeyword() {
    TrendKeyword trendKeyword = trendKeywordAPI.request();

    if (trendKeyword == null)
      throw new NoContentsException("no contents");

    return trendKeyword.toEntity();
  }

  private List<Youtube> loadYoutube(String keyword) {
    List<Youtube> youtubeList = youtubeAPI.request(keyword, DISPLAY);

    if (youtubeList == null || youtubeList.size() == 0)
      throw new NoContentsException("no contents");

    return youtubeList;
  }

  private void saveNewsData(List<Keyword> keywords) {
    for (Keyword keyword : keywords) {
      String query = keyword.getName();

      NaverNewsModel naverNewsModel = naverNewsAPI.request(query, NEWS_SORTING, DISPLAY);

      List<NaverNews> naverNewsList = new ArrayList<>();

      for (NaverNews naverNews : naverNewsModel.toEntity())
        naverNewsList.add(naverNews);
      keyword.setNaverNewsList(naverNewsList);
    }
  }

}
