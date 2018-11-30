package com.naver.hackday.trendviewer.restservice.openapi.naver;

import com.naver.hackday.trendviewer.restservice.openapi.exception.InternalAPIServerErrorException;
import com.naver.hackday.trendviewer.restservice.openapi.naver.model.NaverNewsModel;
import java.time.Duration;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class NaverNewsAPI {

  private static final String API_URL = "https://openapi.naver.com/v1/search/news.json";

  @Value("${api.naver.news.client-id}")
  private String CLIENT_ID;

  @Value("${api.naver.news.client-secret}")
  private String CLIENT_SECRET;

  @Value("${api.duration.connection-time}")
  private int CONNECTION_TIME;

  @Value("${api.duration.read-time}")
  private int READ_TIME;

  public NaverNewsModel request(String query, String sort, int display) {
    ResponseEntity<NaverNewsModel> naverNews = requestAPI(query, sort, display);

    if (naverNews.getStatusCode() != HttpStatus.OK)
      throw new InternalAPIServerErrorException("no api response exception");

    return naverNews.getBody();
  }

  protected ResponseEntity<NaverNewsModel> requestAPI(String query, String sort, int display) {
    String requestUrl = createRequestUrl(query, sort, display);
    RestTemplate restTemplate = new RestTemplateBuilder()
        .setReadTimeout(Duration.ofSeconds(CONNECTION_TIME))
        .setConnectTimeout(Duration.ofSeconds(READ_TIME))
        .build();

    HttpHeaders header = new HttpHeaders();
    header.add("X-Naver-Client-Id", CLIENT_ID);
    header.add("X-Naver-Client-Secret", CLIENT_SECRET);

    return restTemplate.exchange(requestUrl, HttpMethod.GET, new HttpEntity(header), NaverNewsModel.class);
  }

  private String createRequestUrl(String query, String sort, int display) {
    return new StringBuilder(API_URL)
        .append("?query=")
        .append(query)
        .append("&display=")
        .append(display)
        .append("&sort=")
        .append(sort)
        .toString();
  }
}
