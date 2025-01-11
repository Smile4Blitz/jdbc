package com.example.jdbcapi.repository;

import com.example.jdbcapi.model.Example;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ExampleRepository {

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public ExampleRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public Example findById(Long id) {
        String sql = "SELECT * FROM examples WHERE id = ?";
        return jdbcTemplate.queryForObject(sql, new Object[]{id}, new BeanPropertyRowMapper<>(Example.class));
    }

    public List<Example> findAll() {
        String sql = "SELECT * FROM examples";
        return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(Example.class));
    }

    public Example save(Example example) {
        String sql = "INSERT INTO examples (name, description) VALUES (?, ?)";
        jdbcTemplate.update(sql, example.getName(), example.getDescription());
        String queryLastInserted = "SELECT * FROM examples ORDER BY id DESC LIMIT 1";
        return jdbcTemplate.queryForObject(queryLastInserted, new BeanPropertyRowMapper<>(Example.class));
    }

    public Example update(Long id, Example example) {
        String sql = "UPDATE examples SET name = ?, description = ? WHERE id = ?";
        jdbcTemplate.update(sql, example.getName(), example.getDescription(), id);
        return findById(id);
    }

    public void delete(Long id) {
        String sql = "DELETE FROM examples WHERE id = ?";
        jdbcTemplate.update(sql, id);
    }
}