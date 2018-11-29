package com.naver.hackday.trendviewer.restservice.controller;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.naver.hackday.trendviewer.restservice.model.Keyword;
import com.naver.hackday.trendviewer.restservice.repository.KeywordRepository;

@RestController
public class KeywordController {
	
	@Autowired
	private KeywordRepository keywordRepository;
	@GetMapping("/keyword")
	public List<Keyword> getKeywords(
			@RequestParam(name = "date")
			@DateTimeFormat(iso = ISO.DATE_TIME)
			LocalDateTime created_time) {
		List<Keyword> keywords = keywordRepository.findByCreatedTime(created_time);
		return keywords;
	}
}
