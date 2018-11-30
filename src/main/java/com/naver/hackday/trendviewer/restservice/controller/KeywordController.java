package com.naver.hackday.trendviewer.restservice.controller;

import com.naver.hackday.trendviewer.restservice.model.Keyword;
import com.naver.hackday.trendviewer.restservice.repository.KeywordRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class KeywordController {

	@Autowired
	private KeywordRepository keywordRepository;

	@GetMapping("/keyword")
	public List<Keyword> getKeyword() {
		return keywordRepository.findAll();
	}
}
