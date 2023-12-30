package com.practice.executor;

import com.practice.ProcessConstant;
import com.practice.repository.ProcessorRepository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Callable;

public class RepositoryExecutor implements Callable<Map<String, Object>> {

    private int id;
    private ProcessorRepository repository;

    public RepositoryExecutor(int id, ProcessorRepository repository) {
        this.id = id;
        this.repository = repository;
    }

    @Override
    public Map<String, Object> call() {
        Map<String, Object> resultMap = new HashMap<>();
        Map<String, Object> successMap;

        try {
            List<Map<String, Object>> resultDataList = repository.getDataById(id);
            successMap = resultDataList.get(0);
            resultMap.putAll(successMap);
        } catch (Exception exception) {
            resultMap.put(ProcessConstant.FAILURE_RESULTS, id);
        }
        return resultMap;
    }

}
