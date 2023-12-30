package com.practice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

@SpringBootApplication
@EnableAsync
public class SpringbootExceptionThreadsApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringbootExceptionThreadsApplication.class, args);
    }

}
