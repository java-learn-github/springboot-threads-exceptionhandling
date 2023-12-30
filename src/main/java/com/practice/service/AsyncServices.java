package com.practice.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import java.util.List;

@Slf4j
@Component
public class AsyncServices {

    @Async
    public void callExceptionMethod(List<Object> failureIds) {
        log.error("CALL THE EXCEPTION SERVICE TO STORE THE EXCEPTION FOR {}", failureIds);
    }
}
