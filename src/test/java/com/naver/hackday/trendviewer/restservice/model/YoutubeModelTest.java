package com.naver.hackday.trendviewer.restservice.model;

import com.naver.hackday.trendviewer.restservice.openapi.youtube.model.Youtube;
import com.naver.hackday.trendviewer.restservice.repository.KeywordRepository;
import com.naver.hackday.trendviewer.restservice.repository.YoutubeRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDateTime;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;


@RunWith(SpringRunner.class)
@SpringBootTest
public class YoutubeModelTest {
    @Autowired
    KeywordRepository keywordRepository;
    @Autowired
    YoutubeRepository youtubeRepository;

    @Test
    public void youtubeModelTest() {
        Keyword keyword = new Keyword();
        keyword.setName("맛동산");
        keyword.setCreatedTime(LocalDateTime.now());
        keyword.setRank(1);
        keywordRepository.save(keyword);

        Youtube youtube1 = new Youtube();
        youtube1.setThumbnail("www.youtube.com");
        youtube1.setTitle("맛동산 먹방");
        youtube1.setUrl("www.youtube.com");
        youtube1.setKeyword(keyword);

        Youtube youtube2 = new Youtube();
        youtube2.setThumbnail("www.youtube.com");
        youtube2.setTitle("맛동산 먹방2");
        youtube2.setUrl("www.youtube.com");
        youtube2.setKeyword(keyword);

        youtubeRepository.save(youtube1);
        youtubeRepository.save(youtube2);

        List<Youtube> youtubes = youtubeRepository.findAll();
        for (int i = 0; i < youtubes.size(); i++) {
            assertThat(youtubes.get(i)).isEqualTo(i + 1);
        }
    }
}

