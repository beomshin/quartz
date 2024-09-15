package com.kr.quartz.jobs;

import lombok.extern.slf4j.Slf4j;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.springframework.stereotype.Component;

/**
 * Quartz 자체의 기본 인터페이스
 * Quartz 스케줄러에서 직접 인스턴스화하여 사용
 * 상태 저장 불가능 (매번 새로운 인스턴스 생성)
 *
 */
@Slf4j
@Component
public class MyJob implements Job { //

    @Override
    public void execute(JobExecutionContext jobExecutionContext)  {
      log.info("MyJob executing...");
      log.info("Job instance hash code: {}", this.hashCode());
    }

}
