package com.naver.hackday.trendviewer.restservice.repository;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import com.naver.hackday.trendviewer.restservice.model.Keyword;

public interface KeywordRepository extends JpaRepository<Keyword, Integer>{
	
	List<Keyword> findByCreatedTime(@Param(value="date") LocalDateTime created_time);
}
