package com.kr.quartz.triggers;

import com.kr.quartz.jobs.MyJob;
import com.kr.quartz.jobs.MyQuartzJob;
import lombok.extern.slf4j.Slf4j;
import org.quartz.*;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class MyQuartzJobTrigger {

    public MyQuartzJobTrigger(SchedulerFactoryBean schedulerFactoryBean) throws SchedulerException {

        var jobDetail = JobBuilder
                .newJob(MyQuartzJob.class)
                .withIdentity("myQuartzJob", "group1")
                .withDescription("my job description")
                .requestRecovery(true)
                .storeDurably(true)
                .usingJobData("key", "val")
                .build();

        var trigger = TriggerBuilder.newTrigger()
                .withIdentity("myQuartzTrigger", "group1")
                .startNow()
                .withSchedule(
                        CronScheduleBuilder.cronSchedule("0/5 * * * * ?")
                )
                .build();

        schedulerFactoryBean.getScheduler().scheduleJob(jobDetail, trigger);
    }

}
