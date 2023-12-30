package com.practice.controller;

import com.practice.exception.ProcessorException;
import com.practice.service.ProcessorService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutionException;

@RestController
public class ProcessController {

    private ProcessorService service;

    public ProcessController(ProcessorService service) {
        this.service = service;
    }

    @PostMapping("/getbyids")
    public ResponseEntity<Object> getAllData(@RequestBody List<Integer> ids) throws InterruptedException, ExecutionException, ProcessorException {
        List<Map<String, Object>> allData = service.getAllData(ids);
        return ResponseEntity.ok(allData);
    }
}
