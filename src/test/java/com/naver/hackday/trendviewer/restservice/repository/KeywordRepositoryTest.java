package com.naver.hackday.trendviewer.restservice.repository;

import static org.hamcrest.Matchers.not;
import static org.junit.Assert.assertThat;

import com.naver.hackday.trendviewer.restservice.model.Keyword;
import com.naver.hackday.trendviewer.restservice.openapi.youtube.model.Youtube;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Profile;
import org.springframework.test.context.junit4.SpringRunner;

@Profile("dev")
@RunWith(SpringRunner.class)
@SpringBootTest
public class KeywordRepositoryTest {

  @Autowired
  private KeywordRepository keywordRepository;

  @Autowired
  private YoutubeRepository youtubeRepository;

  @Before
  public void init() {
    keywordRepository.deleteAll();
  }

  @Test
  public void insertKeywordData() {
    Keyword keyword = new Keyword().builder()
        .name("와썹맨")
        .rank(1)
        .createdTime(LocalDateTime.now())
        .build();
    Youtube youtube = new Youtube().builder()
        .title("와썹맨")
        .thumbnail("thumbnail")
        .url("https://www.youtube.com")
        .build();

    keyword.setYoutubeList(Arrays.asList(youtube));
    keywordRepository.save(keyword);

    List<Keyword> loadKeyword = keywordRepository.findAll();
    assertThat(loadKeyword.get(0).getYoutubeList(), not(null));
  }
}
