package com.kr.quartz.listener;

import lombok.extern.slf4j.Slf4j;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.quartz.JobListener;
import org.springframework.stereotype.Component;

/**
 * 로깅용으로 사용하는 job 리스너
 */
@Slf4j
public class MyJobListener implements JobListener {

    @Override
    public String getName() {
        return getClass().getSimpleName();
    }

    @Override
    public void jobToBeExecuted(JobExecutionContext jobExecutionContext) {
//        log.info("Job 시작"); // job 실행 시점
    }

    @Override
    public void jobExecutionVetoed(JobExecutionContext jobExecutionContext) {
        log.info("jobExecutionVetoed"); // 트리거 veto 실행시
    }

    @Override
    public void jobWasExecuted(JobExecutionContext jobExecutionContext, JobExecutionException e) {
//        log.info("Job 종료"); // job 종료 시점
    }
}
