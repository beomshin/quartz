package com.kr.quartz.config;

import com.kr.quartz.listener.MyJobListener;
import com.kr.quartz.listener.TriggerListener;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;
import org.springframework.scheduling.quartz.SpringBeanJobFactory;

@Slf4j
@Configuration
public class SchedulerConfig {


    @Bean
    public SchedulerFactoryBean schedulerFactoryBean(ApplicationContext applicationContext) {
        var schedulerFactoryBean = new SchedulerFactoryBean();

        var jobFactory = new SpringBeanJobFactory();

        schedulerFactoryBean.setJobFactory(jobFactory); // job factory 등록
        schedulerFactoryBean.setApplicationContext(applicationContext); // context 등록

        schedulerFactoryBean.setOverwriteExistingJobs(false); // job 중복 허용 (덮어쓰기)
        schedulerFactoryBean.setWaitForJobsToCompleteOnShutdown(true); // shutdown wait 속성

        schedulerFactoryBean.setGlobalTriggerListeners(new TriggerListener());
        schedulerFactoryBean.setGlobalJobListeners(new MyJobListener()); // job 리스너 등록

        return schedulerFactoryBean;
    }

}
