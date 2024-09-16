package com.kr.quartz.triggers;

import com.kr.quartz.jobs.InvokingJob;
import com.kr.quartz.jobs.MyQuartzJob;
import lombok.extern.slf4j.Slf4j;
import org.quartz.JobBuilder;
import org.quartz.SchedulerException;
import org.quartz.SimpleScheduleBuilder;
import org.quartz.TriggerBuilder;
import org.springframework.context.annotation.Profile;
import org.springframework.scheduling.quartz.MethodInvokingJobDetailFactoryBean;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@Profile("invoking") // h2 quartz 데이터베이스 생성 시  methodInvoker 직렬화 에러 발생
public class InvokingJobTrigger {

    public InvokingJobTrigger(SchedulerFactoryBean schedulerFactoryBean) throws SchedulerException, ClassNotFoundException, NoSuchMethodException {

        var jobDetail = new MethodInvokingJobDetailFactoryBean();
        jobDetail.setTargetObject(new InvokingJob());
        jobDetail.setTargetMethod("run"); // 타겟 메소드
        jobDetail.setName("invokingJob");
        jobDetail.setGroup("invokingJobGroup");
        jobDetail.afterPropertiesSet();

        var trigger = TriggerBuilder.newTrigger()
                .withIdentity("invokingJobTrigger", "group1")
                .startNow()
                .withSchedule(
                        SimpleScheduleBuilder.simpleSchedule()
                                .withIntervalInSeconds(5)
                                .repeatForever()
                )
                .build();


        schedulerFactoryBean.getScheduler().scheduleJob(jobDetail.getObject(), trigger);
    }

}
