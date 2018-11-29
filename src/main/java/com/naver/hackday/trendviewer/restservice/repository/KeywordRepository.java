package com.naver.hackday.trendviewer.restservice.repository;

import com.naver.hackday.trendviewer.restservice.model.Keyword;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
import java.util.Set;

@RepositoryRestResource(collectionResourceRel = "keyword", path = "keyword")
public interface KeywordRepository extends JpaRepository<Keyword, Long> {
    Set<Keyword> findByCreatedTime(@Param("createdTime") Date createdTime);
    List<Keyword> findByCreatedTime(@Param(value="date") LocalDateTime created_time);
}
