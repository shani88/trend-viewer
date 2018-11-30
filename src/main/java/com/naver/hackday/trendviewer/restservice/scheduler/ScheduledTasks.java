package com.naver.hackday.trendviewer.restservice.scheduler;

import com.naver.hackday.trendviewer.restservice.service.TrendKeywordService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class ScheduledTasks {

  private static final Logger logger = LoggerFactory.getLogger(ScheduledTasks.class);

  @Autowired
  private TrendKeywordService trendKeywordService;

  // every 30minutes -> */30 * * * *
  @Scheduled(cron = "0 0/30 * * * ?")
  public void collectDataScheduler() {
    trendKeywordService.collectData();
  }

}
