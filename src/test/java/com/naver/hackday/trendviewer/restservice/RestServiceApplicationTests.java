package com.naver.hackday.trendviewer.restservice;


import com.naver.hackday.trendviewer.restservice.model.Keyword;
import com.naver.hackday.trendviewer.restservice.repository.KeywordRepository;
import com.naver.hackday.trendviewer.restservice.repository.TestRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDateTime;
import java.time.Month;
import java.util.List;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest
public class RestServiceApplicationTests {
    @Autowired
    TestRepository repository;

    @Autowired
    KeywordRepository keywordRepository;


    @Test
    public void di() {
        assertThat(repository).isNotNull();
    }

//    @Test
//    public void test() {
//        TestModel testModel = new TestModel();
//        testModel.setName("BYE TEST");
//
//        assertThat(repository.findByNameIsLikeIgnoreCase("%bye%")).isEmpty();
//        repository.save(testModel);
//        assertThat(repository.findByNameIsLikeIgnoreCase("%bye%")).hasSize(1);
//    }

    @Test
    public void keywordAPITest() {
        System.out.println(LocalDateTime.of(2018, 11, 29, 18, 0));
        System.out.println(keywordRepository.findAll().get(0));
        List<Keyword> keywordSet = keywordRepository.findByCreatedTime(LocalDateTime.of(2018, 11, 29, 18, 0));
        assertThat(keywordSet.size()).isEqualTo(1);
    }

}
