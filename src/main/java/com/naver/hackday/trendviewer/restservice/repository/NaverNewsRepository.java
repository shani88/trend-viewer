package com.naver.hackday.trendviewer.restservice.repository;

import com.naver.hackday.trendviewer.restservice.model.NaverNews;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NaverNewsRepository extends JpaRepository<NaverNews, Long> {

}
