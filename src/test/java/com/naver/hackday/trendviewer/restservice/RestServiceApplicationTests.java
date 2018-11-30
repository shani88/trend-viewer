package com.naver.hackday.trendviewer.restservice;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import com.naver.hackday.trendviewer.restservice.model.Keyword;
import com.naver.hackday.trendviewer.restservice.repository.KeywordRepository;
import com.naver.hackday.trendviewer.restservice.repository.TestRepository;
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
public class RestServiceApplicationTests {
    @Autowired
    TestRepository repository;

    @Autowired
    KeywordRepository keywordRepository;

    @Before
    public void init() {
        keywordRepository.deleteAll();
    }


    @Test
    public void di() {
        assertThat(repository).isNotNull();
    }

    @Test
    public void saveKeyword() {
        Keyword keyword = new Keyword().builder()
            .name("테스트")
            .rank(1)
            .createdTime(LocalDateTime.now())
            .build();
        keywordRepository.save(keyword);

        assertThat(keywordRepository.count(), is(1L));
    }

}
