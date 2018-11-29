package com.naver.hackday.trendviewer.restservice.openapi.naver;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import com.naver.hackday.trendviewer.restservice.openapi.naver.model.TrendKeyword;
import java.util.List;
import org.junit.Before;
import org.junit.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class TrendKeywordAPITest {

  private TrendKeywordAPI api;

  @Before
  public void init() {
    api = new TrendKeywordAPI();
  }

  @Test
  public void callApiRequest() {
    ResponseEntity<String> responseEntity = api.requestAPI();
    assertThat(responseEntity.getStatusCode(), is(HttpStatus.OK));
  }

  @Test
  public void responseToEntity() {
    List<TrendKeyword> trendKeywordList = api.request();
    assertThat(trendKeywordList.size(), is(20));
  }

}
