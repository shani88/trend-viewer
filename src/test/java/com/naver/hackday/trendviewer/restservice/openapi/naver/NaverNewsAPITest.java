package com.naver.hackday.trendviewer.restservice.openapi.naver;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import com.naver.hackday.trendviewer.restservice.openapi.naver.model.NaverNewsModel;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Profile;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
@Profile("dev")
public class NaverNewsAPITest {

  @Autowired
  private NaverNewsAPI naverNewsAPI;

  @Test
  public void requestDisplay5() {
    NaverNewsModel naverNews = naverNewsAPI.request("naver", "date", 5);
    assertThat(naverNews.getDisplay(), is(5));
  }

  @Test
  public void requestAPI() {
    ResponseEntity<NaverNewsModel> responseEntity = naverNewsAPI.requestAPI("naver", "date", 5);
    assertThat(responseEntity.getStatusCode(), is(HttpStatus.OK));
  }

}
