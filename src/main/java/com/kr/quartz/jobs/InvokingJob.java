package com.kr.quartz.jobs;

import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * 리플렉션을 이용하여 targetMethod로 실행될수있게 설정
 */
@Slf4j
@NoArgsConstructor
public class InvokingJob {

    public void run() {
        log.info("InvokingJob executing...");
        log.info("Job instance hash code: {}", this.hashCode());
    }

}
