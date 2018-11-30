package com.naver.hackday.trendviewer.restservice.service;

import com.naver.hackday.trendviewer.restservice.openapi.youtube.model.Youtube;
import com.naver.hackday.trendviewer.restservice.repository.KeywordRepository;
import com.naver.hackday.trendviewer.restservice.repository.YoutubeRepository;
import java.util.List;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Profile;
import org.springframework.test.context.junit4.SpringRunner;

@SpringBootTest
@RunWith(SpringRunner.class)
@Profile("dev")
public class YoutubeSaveTest {
	@Autowired
	private TrendKeywordService trendKeywordService;

	@Autowired
	private YoutubeRepository youtubeRepository;

	@Autowired
	private KeywordRepository keywordRepository;

	/*
	@Before
	public void init() {
		keywordRepository.deleteAll();
		List<NaverNews> nullList_naverNews = new ArrayList<>();
		List<Youtube> nullList_youtube = new ArrayList<>();
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
		LocalDateTime dateTime = LocalDateTime.parse("2018-11-30 11:18:00", formatter);

		keywordRepository.save(new Keyword("와썹맨", dateTime, 1, nullList_naverNews, nullList_youtube));
	}
	*/

	@Test
	public void youtubeRequestTest() {
		trendKeywordService.collectData();
		List<Youtube> youtubeList = youtubeRepository.findAll();
		//assertThat(youtubeList.size(), is(5));
	}
}
