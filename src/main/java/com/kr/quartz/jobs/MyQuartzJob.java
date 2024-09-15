package com.kr.quartz.jobs;

import com.kr.quartz.service.MyService;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.quartz.DisallowConcurrentExecution;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.scheduling.quartz.QuartzJobBean;
import org.springframework.stereotype.Component;

/**
 * QuartzJobBean은 스프링에서 제공하는 Quartz 통합 지원 클래스
 * Spring Bean으로 관리되며, 의존성 주입 가능, 상태 저장 가능 (Spring의 싱글톤 Bean으로 관리)
 */
@Slf4j
@Component
@RequiredArgsConstructor
@DisallowConcurrentExecution // 동시 실행 방지
@NoArgsConstructor(access = AccessLevel.NONE)
public class MyQuartzJob extends QuartzJobBean {

    private final MyService myService;

    @Override
    protected void executeInternal(JobExecutionContext context) throws JobExecutionException {
        log.info("MyQuartzJob executing...");
        myService.myMethod(); // 의존성 주입 확인
        log.info("MyQuartzJob instance hash code: {}", this.hashCode());

        try {
            log.info("Thread start");
            Thread.sleep(10000); // 동시 실행 방지 테스트
            log.info("Thread end");
        } catch (InterruptedException ignored) {}
    }

}
