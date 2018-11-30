package com.naver.hackday.trendviewer.restservice.openapi.naver;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.naver.hackday.trendviewer.restservice.openapi.exception.InternalAPIServerErrorException;
import com.naver.hackday.trendviewer.restservice.openapi.naver.model.KeywordModel;
import com.naver.hackday.trendviewer.restservice.openapi.naver.model.TrendKeyword;
import java.io.IOException;
import java.time.Duration;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class TrendKeywordAPI {

  private final static Logger logger = LoggerFactory.getLogger(TrendKeywordAPI.class);

  private final static String API_URL = "http://106.10.35.9/trendKeyword?sort=createTime";

  @Value("${api.duration.connection-time}")
  private int CONNECTION_TIME;

  @Value("${api.duration.read-time}")
  private int READ_TIME;

  public TrendKeyword request() {
    ResponseEntity<String> responseEntity = requestAPI();
    TrendKeyword trendKeyword = null;

    if (responseEntity.getStatusCode() != HttpStatus.OK)
      throw new InternalAPIServerErrorException("no api response exception");

    String json = responseEntity.getBody();
    try {
      ObjectMapper mapper = new ObjectMapper();
      Map<String, Object> map = mapper.readValue(json, new TypeReference<HashMap<String, Object>>(){});
      Map<String, Object> embedded = (Map<String, Object>) map.get("_embedded");
      List<Map<String, Object>> keywordList = (List<Map<String, Object>>) embedded.get("trendKeyword");
      trendKeyword = mapToTrendKeyword(keywordList.get(0));
    } catch (IOException e) {
      logger.error("mapper readValue exception");
    }

    return trendKeyword;
  }

  protected ResponseEntity<String> requestAPI() {
    RestTemplate restTemplate = new RestTemplateBuilder()
        .setConnectTimeout(Duration.ofSeconds(CONNECTION_TIME))
        .setReadTimeout(Duration.ofSeconds(READ_TIME))
        .build();

    return restTemplate.exchange(API_URL,
          HttpMethod.GET, null, String.class);
  }

  private TrendKeyword mapToTrendKeyword(Map<String, Object> map) {
    int rank = 1;
    TrendKeyword trendKeyword = new TrendKeyword();
    List<String> keywordStringList = (List<String>) map.get("keywordList");
    List<KeywordModel> keywords = new ArrayList<>();

    for (String key : keywordStringList) {
      KeywordModel keyword = new KeywordModel(rank, key);
      keywords.add(keyword);
      rank++;
    }

    trendKeyword.setCreatedTime((String) map.get("createdTime"));
    trendKeyword.setKeywordList(keywords);

    return trendKeyword;
  }

}
