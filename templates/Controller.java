package com.example.jdbcapi.controller;

import com.example.jdbcapi.model.Example;
import com.example.jdbcapi.service.ExampleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/examples")
public class ExampleController {

    private final ExampleService exampleService;

    @Autowired
    public ExampleController(ExampleService exampleService) {
        this.exampleService = exampleService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<Example> getExampleById(@PathVariable Long id) {
        return ResponseEntity.ok(exampleService.getExampleById(id));
    }

    @GetMapping
    public ResponseEntity<List<Example>> getAllExamples() {
        return ResponseEntity.ok(exampleService.getAllExamples());
    }

    @PostMapping
    public ResponseEntity<Example> createExample(@RequestBody Example example) {
        return ResponseEntity.ok(exampleService.createExample(example));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Example> updateExample(@PathVariable Long id, @RequestBody Example example) {
        return ResponseEntity.ok(exampleService.updateExample(id, example));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteExample(@PathVariable Long id) {
        exampleService.deleteExample(id);
        return ResponseEntity.noContent().build();
    }
}