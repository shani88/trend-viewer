package com.naver.hackday.trendviewer.restservice.openapi.naver;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.not;
import static org.junit.Assert.assertThat;

import com.naver.hackday.trendviewer.restservice.openapi.naver.model.TrendKeyword;
import java.util.List;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TrendKeywordAPITest {

  @Autowired
  private TrendKeywordAPI api;

  @Test
  public void callApiRequest() {
    ResponseEntity<String> responseEntity = api.requestAPI();
    assertThat(responseEntity.getStatusCode(), is(HttpStatus.OK));
  }

  @Test
  public void responseToEntity() {
    TrendKeyword trendKeyword = api.request();
    assertThat(trendKeyword, not(null));
  }

}
