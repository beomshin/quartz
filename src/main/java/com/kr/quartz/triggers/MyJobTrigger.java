package com.kr.quartz.triggers;

import com.kr.quartz.jobs.MyJob;
import com.kr.quartz.jobs.MyQuartzJob;
import lombok.extern.slf4j.Slf4j;
import org.quartz.*;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class MyJobTrigger {

    public MyJobTrigger(SchedulerFactoryBean schedulerFactoryBean) throws SchedulerException {

        var jobDetail = JobBuilder
                .newJob(MyJob.class)
                .withIdentity("myJob", "group1")
                .withDescription("my job description")
                .requestRecovery(true)
                .storeDurably(true)
                .usingJobData("key", "val")
                .build();

        var trigger = TriggerBuilder.newTrigger()
                .withIdentity("myJobTrigger", "group1")
                .startNow()
                .withSchedule(
                        SimpleScheduleBuilder.simpleSchedule()
                                .withIntervalInSeconds(20)
                                .repeatForever()
                )
                .build();

        if (schedulerFactoryBean.getObject().checkExists(jobDetail.getKey())) {
            return;
        }

        schedulerFactoryBean.getScheduler().scheduleJob(jobDetail, trigger);
    }

}
