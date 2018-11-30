package com.naver.hackday.trendviewer.restservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.naver.hackday.trendviewer.restservice.model.NaverNews;

public interface NaverNewsRepository extends JpaRepository<NaverNews, Integer> {
}
