package com.kr.quartz.listener;

import lombok.extern.slf4j.Slf4j;
import org.quartz.JobExecutionContext;
import org.quartz.Trigger;

@Slf4j
public class TriggerListener implements org.quartz.TriggerListener {

    @Override
    public String getName() {
        return getClass().getSimpleName();
    }

    @Override
    public void triggerFired(Trigger trigger, JobExecutionContext jobExecutionContext) {
        log.info("triggerFired"); // 트리거 수행시점
    }

    @Override
    public boolean vetoJobExecution(Trigger trigger, JobExecutionContext jobExecutionContext) {
        return false;
    }

    @Override
    public void triggerMisfired(Trigger trigger) {
        log.info("triggerMisfired"); // 트리거 수행 실패
    }

    @Override
    public void triggerComplete(Trigger trigger, JobExecutionContext jobExecutionContext, Trigger.CompletedExecutionInstruction completedExecutionInstruction) {
        log.info("triggerComplete"); // 트리거 수행 완료
    }
}
