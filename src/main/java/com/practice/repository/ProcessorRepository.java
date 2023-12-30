package com.practice.repository;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public class ProcessorRepository {

    private JdbcTemplate jdbcTemplate;

    public ProcessorRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<Map<String, Object>> getDataById(int id) {
        return jdbcTemplate.queryForList("select * from mock_data where id = " + id);
    }

}
