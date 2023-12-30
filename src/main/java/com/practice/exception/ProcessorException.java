package com.practice.exception;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ProcessorException extends Exception {
    private static final long serialVersionUID = 1L;

    private final String errorCode;

    public ProcessorException(String errorCode) {
        this.errorCode = errorCode;
    }

    public void handleException() {
        log.warn("WRITE A CODE TO HANDLE THE EXCEPTION FOR {}", errorCode);
    }

}
