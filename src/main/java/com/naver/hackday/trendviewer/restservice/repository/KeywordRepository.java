package com.naver.hackday.trendviewer.restservice.repository;

import com.naver.hackday.trendviewer.restservice.model.Keyword;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.time.LocalDateTime;
import java.util.List;

/**
 * @author SaeHan Kim
 * @since 2018-11-28
 */
@RepositoryRestResource(collectionResourceRel = "keyword", path = "keyword")
public interface KeywordRepository extends JpaRepository<Keyword, Long> {
    List<Keyword> findByCreatedTime(@Param("createdTime") LocalDateTime createdTime);
}
