package com.naver.hackday.trendviewer.restservice.openapi.naver;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.naver.hackday.trendviewer.restservice.openapi.exception.InternalAPIServerErrorException;
import com.naver.hackday.trendviewer.restservice.openapi.naver.model.KeywordModel;
import com.naver.hackday.trendviewer.restservice.openapi.naver.model.TrendKeyword;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class TrendKeywordAPI {

  private final static Logger logger = LoggerFactory.getLogger(TrendKeywordAPI.class);

  private final static String API_URL = "http://106.10.35.9/trendKeyword";

  public List<TrendKeyword> request() {
    ResponseEntity<String> responseEntity = requestAPI();

    if (responseEntity.getStatusCode() != HttpStatus.OK)
      throw new InternalAPIServerErrorException("no api response exception");

    String json = responseEntity.getBody();
    List<TrendKeyword> trendKeywords = new ArrayList<>();

    try {
      ObjectMapper mapper = new ObjectMapper();
      Map<String, Object> map = mapper.readValue(json, new TypeReference<HashMap<String, Object>>(){});
      Map<String, Object> embedded = (Map<String, Object>) map.get("_embedded");
      List<Map<String, Object>> keywordList = (List<Map<String, Object>>) embedded.get("trendKeyword");

      trendKeywords = keywordList.stream().map(this::mapToTrendKeyword).collect(Collectors.toList());
    } catch (IOException e) {
      logger.error("mapper readValue exception");
    }

    return trendKeywords;
  }

  protected ResponseEntity<String> requestAPI() {
    RestTemplate restTemplate = new RestTemplate();
    ResponseEntity<String> responseEntity = restTemplate.exchange(API_URL,
        HttpMethod.GET, null, String.class);

    return responseEntity;
  }

  private TrendKeyword mapToTrendKeyword(Map<String, Object> map) {
    int rank = 1;
    TrendKeyword trendKeyword = new TrendKeyword();
    List<String> keywordStringList = (List<String>) map.get("keywordList");
    List<KeywordModel> keywords = new ArrayList<>();

    for (String key : keywordStringList) {
      KeywordModel keyword = new KeywordModel(rank, key);
      keywords.add(keyword);
    }

    trendKeyword.setCreatedTime((String) map.get("createdTime"));
    trendKeyword.setKeywordList(keywords);

    return trendKeyword;
  }

}
