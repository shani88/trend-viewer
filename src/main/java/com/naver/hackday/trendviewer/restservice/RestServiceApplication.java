package com.naver.hackday.trendviewer.restservice;

import com.naver.hackday.trendviewer.restservice.service.TrendKeywordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.convert.threeten.Jsr310JpaConverters;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableScheduling
@SpringBootApplication
@EntityScan(basePackageClasses = { RestServiceApplication.class, Jsr310JpaConverters.class })
public class RestServiceApplication implements CommandLineRunner {

	@Autowired
	private TrendKeywordService trendKeywordService;

	public static void main(String[] args) {
		SpringApplication.run(RestServiceApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		trendKeywordService.collectData();
	}
}
