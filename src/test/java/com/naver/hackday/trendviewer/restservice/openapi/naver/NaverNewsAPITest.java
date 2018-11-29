package com.naver.hackday.trendviewer.restservice.openapi.naver;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import com.naver.hackday.trendviewer.restservice.openapi.naver.model.NaverNews;
import org.junit.Before;
import org.junit.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class NaverNewsAPITest {

  private NaverNewsAPI naverNewsAPI;

  @Before
  public void init() {
    naverNewsAPI = new NaverNewsAPI();
  }

  @Test
  public void requestDisplay5() {
    NaverNews naverNews = naverNewsAPI.request("naver", "date", 5);
    assertThat(naverNews.getDisplay(), is(5));
  }

  @Test
  public void requestAPI() {
    ResponseEntity<NaverNews> responseEntity = naverNewsAPI.requestAPI("naver", "date", 5);
    assertThat(responseEntity.getStatusCode(), is(HttpStatus.OK));
  }

}
