package com.kr.quartz.listener;

import lombok.extern.slf4j.Slf4j;
import org.quartz.JobExecutionContext;
import org.quartz.Trigger;
import org.quartz.TriggerListener;

@Slf4j
public class MyTriggerListener implements TriggerListener {

    @Override
    public String getName() {
        return getClass().getSimpleName();
    }

    @Override
    public void triggerFired(Trigger trigger, JobExecutionContext jobExecutionContext) {
//        log.info("triggerFired"); // 트리거 수행시점
    }

    @Override
    public boolean vetoJobExecution(Trigger trigger, JobExecutionContext jobExecutionContext) {
        // 트리거 중지 여부
        return false;
    }

    @Override
    public void triggerMisfired(Trigger trigger) {
        log.info("triggerMisfired"); // 트리거 수행 실패
    }

    @Override
    public void triggerComplete(Trigger trigger, JobExecutionContext jobExecutionContext, Trigger.CompletedExecutionInstruction completedExecutionInstruction) {
//        log.info("triggerComplete"); // 트리거 수행 완료
    }
}
