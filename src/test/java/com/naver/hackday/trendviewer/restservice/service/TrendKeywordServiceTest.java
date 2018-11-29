package com.naver.hackday.trendviewer.restservice.service;

import com.naver.hackday.trendviewer.restservice.model.Keyword;
import com.naver.hackday.trendviewer.restservice.repository.KeywordRepository;
import java.util.List;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Profile;
import org.springframework.test.context.junit4.SpringRunner;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

@SpringBootTest
@RunWith(SpringRunner.class)
@Profile("dev")
public class TrendKeywordServiceTest {

  @Autowired
  private TrendKeywordService trendKeywordService;

  @Autowired
  private KeywordRepository keywordRepository;

  @Before
  public void init() {
    keywordRepository.deleteAll();
  }

  @Test
  public void saveTrendKeywords() {
    trendKeywordService.saveTrendKeyword();
    List<Keyword> keywordList = keywordRepository.findAll();
    assertThat(keywordList.size(), is(400));
  }

}
