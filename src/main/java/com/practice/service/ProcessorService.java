package com.practice.service;

import com.practice.constant.ProcessConstant;
import com.practice.exception.ProcessorException;
import com.practice.executor.RepositoryExecutor;
import com.practice.repository.ProcessorRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

@Service
public class ProcessorService {

    private ProcessorRepository repository;

    private AsyncServices asyncService;

    public ProcessorService(ProcessorRepository repository, AsyncServices asyncService) {
        this.repository = repository;
        this.asyncService = asyncService;

    }

    public List<Map<String, Object>> getAllData(List<Integer> ids) throws InterruptedException, ExecutionException, ProcessorException {

        if (ids.isEmpty()) {
            throw new ProcessorException(ProcessConstant.ERROR_CD_001);
        }

        int threadNum = Math.min(ids.size(), Runtime.getRuntime().availableProcessors());
        ExecutorService executorService = Executors.newFixedThreadPool(threadNum);
        List<Future<Map<String, Object>>> futureTaskList = new ArrayList<>();
        List<Map<String, Object>> resultList = new ArrayList<>();

        for (int id : ids) {
            RepositoryExecutor repositoryExecutor = new RepositoryExecutor(id, repository);
            Future<Map<String, Object>> future = executorService.submit(repositoryExecutor);
            futureTaskList.add(future);
        }

        for (Future<Map<String, Object>> futureTask : futureTaskList) {
            resultList.add(futureTask.get());
        }
        executorService.shutdown();

        List<Object> failureIds = resultList.stream().filter(map -> map.containsKey(ProcessConstant.FAILURE_RESULTS)).map(map -> map.get(ProcessConstant.FAILURE_RESULTS)).toList();
        List<Map<String, Object>> result = resultList.stream().filter(map -> !map.containsKey(ProcessConstant.FAILURE_RESULTS)).toList();
        if (!failureIds.isEmpty()) {
            asyncService.callExceptionMethod(failureIds);
        }

        return result;
    }

}
