package com.kr.quartz.config;

import com.kr.quartz.listener.MyJobListener;
import com.kr.quartz.listener.MyTriggerListener;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;
import org.springframework.scheduling.quartz.SpringBeanJobFactory;

import javax.sql.DataSource;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

@Slf4j
@Configuration
@RequiredArgsConstructor
public class SchedulerConfig {

//    private final DataSource dataSource;

    @Bean
    public SchedulerFactoryBean schedulerFactoryBean(ApplicationContext applicationContext) {
        var schedulerFactoryBean = new SchedulerFactoryBean();

        var jobFactory = new SpringBeanJobFactory();

        schedulerFactoryBean.setJobFactory(jobFactory); // job factory 등록
        schedulerFactoryBean.setApplicationContext(applicationContext); // context 등록

        schedulerFactoryBean.setOverwriteExistingJobs(true); // job 중복 허용 (덮어쓰기)
        schedulerFactoryBean.setWaitForJobsToCompleteOnShutdown(false); // shutdown wait 속성

//        schedulerFactoryBean.setDataSource(dataSource);
        schedulerFactoryBean.setGlobalTriggerListeners(new MyTriggerListener());
        schedulerFactoryBean.setGlobalJobListeners(new MyJobListener()); // job 리스너 등록

        Properties properties = new Properties();
        ClassPathResource classPathResource = new ClassPathResource("/quartz.properties");
        try {
            properties.load(classPathResource.getInputStream());
        } catch (IOException e) {}

        schedulerFactoryBean.setQuartzProperties(properties);

        return schedulerFactoryBean;
    }

}
