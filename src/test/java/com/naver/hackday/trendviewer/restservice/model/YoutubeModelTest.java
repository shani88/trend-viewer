package com.naver.hackday.trendviewer.restservice.model;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import com.naver.hackday.trendviewer.restservice.openapi.youtube.model.Youtube;
import com.naver.hackday.trendviewer.restservice.repository.YoutubeRepository;
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

@RunWith(SpringRunner.class)
@SpringBootTest
@Profile("dev")
public class YoutubeModelTest {
    @Autowired
    YoutubeRepository youtubeRepository;

    @Before
    public void init() {
        youtubeRepository.deleteAll();
    }

    @Test
    public void youtubeModelTest() {
        Keyword keyword = new Keyword();
        keyword.setName("맛동산");
        keyword.setCreatedTime(LocalDateTime.now());
        keyword.setRank(1);

        Youtube youtube1 = new Youtube();
        youtube1.setThumbnail("www.youtube.com");
        youtube1.setTitle("맛동산 먹방");
        youtube1.setUrl("www.youtube.com");

        Youtube youtube2 = new Youtube();
        youtube2.setThumbnail("www.youtube.com");
        youtube2.setTitle("맛동산 먹방2");
        youtube2.setUrl("www.youtube.com");

        List<Youtube> youtubeList = Arrays.asList(youtube1, youtube2);

        youtubeRepository.save(youtube1);
        youtubeRepository.save(youtube2);
        keyword.setYoutubeList(youtubeList);

        assertThat(youtubeRepository.count(), is(2L));
    }
}

