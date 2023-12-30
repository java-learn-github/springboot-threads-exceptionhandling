package com.practice.advice;

import com.practice.exception.ProcessorException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ProcessorAdvice {

    @ExceptionHandler
    @ResponseBody
    public void handleException(ProcessorException ex) {
        ex.handleException();
    }
}
