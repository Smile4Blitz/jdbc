package com.example.jdbcapi.service;

import com.example.jdbcapi.model.Example;
import com.example.jdbcapi.repository.ExampleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ExampleService {

    private final ExampleRepository exampleRepository;

    @Autowired
    public ExampleService(ExampleRepository exampleRepository) {
        this.exampleRepository = exampleRepository;
    }

    public Example getExampleById(Long id) {
        return exampleRepository.findById(id);
    }

    public List<Example> getAllExamples() {
        return exampleRepository.findAll();
    }

    public Example createExample(Example example) {
        return exampleRepository.save(example);
    }

    public Example updateExample(Long id, Example updatedExample) {
        return exampleRepository.update(id, updatedExample);
    }

    public void deleteExample(Long id) {
        exampleRepository.delete(id);
    }
}