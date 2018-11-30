package com.naver.hackday.trendviewer.restservice.openapi.youtube;

import com.naver.hackday.trendviewer.restservice.openapi.youtube.model.Youtube;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Profile;
import org.springframework.test.context.junit4.SpringRunner;

@SpringBootTest
@RunWith(SpringRunner.class)
@Profile("dev")
public class YoutubeAPITest {

	private YoutubeAPI youtubeAPI = new YoutubeAPI();

	private static final Logger logger = LoggerFactory.getLogger(YoutubeAPITest.class);
	
	@Test
	public void youtubeRequestTest() {
		List<Youtube> youtubeList = youtubeAPI.request("와썹맨", 5);
		youtubeList.stream().forEach(youtube -> logger.info(youtube.toString()));
	}

}
