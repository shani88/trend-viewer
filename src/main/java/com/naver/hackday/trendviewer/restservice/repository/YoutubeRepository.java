package com.naver.hackday.trendviewer.restservice.repository;

import com.naver.hackday.trendviewer.restservice.openapi.youtube.model.Youtube;
import org.springframework.data.jpa.repository.JpaRepository;


/**
 * Created by jihun on 2018. 11. 29..
 */
public interface YoutubeRepository extends JpaRepository<Youtube, Long> {
}
