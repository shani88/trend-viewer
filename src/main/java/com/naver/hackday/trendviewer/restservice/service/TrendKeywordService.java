package com.naver.hackday.trendviewer.restservice.service;

import com.naver.hackday.trendviewer.restservice.model.Keyword;
import com.naver.hackday.trendviewer.restservice.openapi.exception.NoContentsException;
import com.naver.hackday.trendviewer.restservice.openapi.naver.NaverNewsAPI;
import com.naver.hackday.trendviewer.restservice.openapi.naver.TrendKeywordAPI;
import com.naver.hackday.trendviewer.restservice.openapi.naver.model.KeywordModel;
import com.naver.hackday.trendviewer.restservice.openapi.naver.model.NaverNewsItems;
import com.naver.hackday.trendviewer.restservice.openapi.naver.model.NaverNewsModel;
import com.naver.hackday.trendviewer.restservice.openapi.naver.model.TrendKeyword;
import com.naver.hackday.trendviewer.restservice.openapi.youtube.YoutubeAPI;
import com.naver.hackday.trendviewer.restservice.openapi.youtube.model.Youtube;
import com.naver.hackday.trendviewer.restservice.repository.KeywordRepository;
import com.naver.hackday.trendviewer.restservice.repository.NaverNewsRepository;
import com.naver.hackday.trendviewer.restservice.repository.YoutubeRepository;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class TrendKeywordService {

  private static final Logger logger = LoggerFactory.getLogger(TrendKeywordService.class);

  private static final String NEWS_SORTING = "date";

  private static final int DISPLAY = 5;

  @Autowired
  private NaverNewsAPI naverNewsAPI;

  @Autowired
  private YoutubeAPI youtubeAPI;

  @Autowired
  private TrendKeywordAPI trendKeywordAPI;

  @Autowired
  private KeywordRepository keywordRepository;
  
  @Autowired
  private NaverNewsRepository naverNewsRepository;
  
  @Autowired
  private YoutubeRepository youtubeRepository;

  @Transactional
  public void collectData() {
	  List<Keyword> keywords = saveTrendKeyword();
	  
//	  saveNewsData(keywords);		//추가
//	  saveYoutubeData(keywords);	//추가
  }

  /***********수정**********/
  protected void saveNewsData(List<KeywordModel> keywords) {
    for (KeywordModel keyword : keywords) {
      String query = keyword.getKeyword();

      NaverNewsModel naverNews = naverNewsAPI.request(query, NEWS_SORTING, DISPLAY);
      List<NaverNewsItems> newsItems = naverNews.getItems();

      for (NaverNewsItems item : newsItems) {
        // Tue, 27 Nov 2018 18:35:00 +0900 (E, dd MMM yyyy hh:mm:ss 
        DateTimeFormatter format = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");	//고쳐야 함 !!
        item.setPubDate(format.toString());
        //naverNewsRepository.save(item);	//NaverNews? NaverNewsItems?
      }
    }
  }
  
  protected void saveYoutubeData(List<KeywordModel> keywords) {
	    for (KeywordModel keyword : keywords) {
	      String query = keyword.getKeyword();

	      List<Youtube> youtubeItems = youtubeAPI.request(query, DISPLAY);
	      
	      for (Youtube item : youtubeItems) {
	        youtubeRepository.save(item);
	      }
	    }
	  }

  protected List<Keyword> saveTrendKeyword() {
    TrendKeyword trendKeyword = trendKeywordAPI.request();
    List<Keyword> savedKeywordList = new ArrayList<>();

    if (trendKeyword == null)
      throw new NoContentsException("no contents");

      List<Keyword> keywordList = trendKeyword.toEntity();
      for (Keyword key : keywordList)
        savedKeywordList.add(keywordRepository.save(key));
      
      return savedKeywordList;
      
  }
}
