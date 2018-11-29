package com.naver.hackday.trendviewer.restservice.repository;

import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

public class KeywordRepositoryTest {

  @Autowired
  private KeywordRepository keywordRepository;

  @Before
  public void init() {
    keywordRepository.deleteAll();
  }
}
