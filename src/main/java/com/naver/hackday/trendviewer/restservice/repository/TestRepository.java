package com.naver.hackday.trendviewer.restservice.repository;

import com.naver.hackday.trendviewer.restservice.model.TestModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.List;

/**
 * @author SaeHan Kim
 * @since 2018-11-28
 */
@RepositoryRestResource(path = "test", collectionResourceRel = "test")
public interface TestRepository extends JpaRepository<TestModel, String> {
    List<TestModel> findByNameIsLikeIgnoreCase(String s);
}
