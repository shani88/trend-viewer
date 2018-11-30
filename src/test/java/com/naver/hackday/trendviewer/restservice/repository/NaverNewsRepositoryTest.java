package com.naver.hackday.trendviewer.restservice.repository;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import com.naver.hackday.trendviewer.restservice.model.NaverNews;
import java.time.LocalDateTime;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Profile;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
@Profile("dev")
public class NaverNewsRepositoryTest {

  @Autowired
  private NaverNewsRepository naverNewsRepository;

  @Before
  public void init() {
    naverNewsRepository.deleteAll();
  }

  @Test
  public void insertNaverNewsData() {
    NaverNews naverNews = new NaverNews().builder()
        .title("뉴스 제목")
        .createdTime(LocalDateTime.now())
        .description("뉴스 설명")
        .link("https://www.naver.com/")
        .build();

    naverNewsRepository.save(naverNews);

    assertThat(naverNewsRepository.count(), is(1L));
  }

}
