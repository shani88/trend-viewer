package com.naver.hackday.trendviewer.restservice;


import com.naver.hackday.trendviewer.restservice.model.TestModel;
import com.naver.hackday.trendviewer.restservice.repository.TestRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest
public class RestServiceApplicationTests {


    @Autowired
    TestRepository repository;

    @Test
    public void di() {
        assertThat(repository).isNotNull();

    }

    @Test
    public void test() {
        TestModel testModel = new TestModel();
        testModel.setName("BYE TEST");

        assertThat(repository.findByNameIsLikeIgnoreCase("%bye%")).isEmpty();
        repository.save(testModel);
        assertThat(repository.findByNameIsLikeIgnoreCase("%bye%")).hasSize(1);
    }

}
