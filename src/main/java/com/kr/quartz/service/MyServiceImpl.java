package com.kr.quartz.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class MyServiceImpl implements MyService {

    @Override
    public void myMethod() {
        log.info("myService");
    }
}
