package com.naver.hackday.trendviewer.restservice.openapi.naver;

import com.naver.hackday.trendviewer.restservice.openapi.exception.InternalAPIServerErrorException;
import com.naver.hackday.trendviewer.restservice.openapi.naver.model.NaverNews;
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

  private static final String CLIENT_ID = "vL0_aPitja6Sa27g8zjM";

  private static final String CLIENT_SECRET = "g5hvicyrBF";

  private static int a = 0;

  public NaverNews request(String query, String sort, int display) {
    ResponseEntity<NaverNews> naverNews = requestAPI(query, sort, display);

    if (naverNews.getStatusCode() != HttpStatus.OK)
      throw new InternalAPIServerErrorException("no api response exception");

    return naverNews.getBody();
  }

  protected ResponseEntity<NaverNews> requestAPI(String query, String sort, int display) {
    String requestUrl = createRequestUrl(query, sort, display);
    RestTemplate restTemplate = new RestTemplate();
    HttpHeaders header = new HttpHeaders();
    header.add("X-Naver-Client-Id", CLIENT_ID);
    header.add("X-Naver-Client-Secret", CLIENT_SECRET);

    return restTemplate.exchange(requestUrl, HttpMethod.GET, new HttpEntity(header), NaverNews.class);
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
