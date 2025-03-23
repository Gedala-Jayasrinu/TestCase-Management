package com.example.TestCaseManagement.controller;
import com.example.TestCaseManagement.model.TestCase;
import com.example.TestCaseManagement.repository.TestCaseRepository;
import com.example.TestCaseManagement.service.TestCaseService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.util.Optional;

@RestController
@RequestMapping("/api/testcases")
@Tag(name = "TestCase API", description = "Operations related to test cases")

public class TestCaseController {

    private final TestCaseService service;
    @Autowired
    private TestCaseRepository repository;

    public TestCaseController(TestCaseService service) {
        this.service = service;
    }

    @GetMapping
    @Operation(summary = "Get all test cases")
    public Page<TestCase> getTestCases(
            @RequestParam(required = false) String status,
            @RequestParam(required = false) String priority,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        return service.getFilteredTestCases(status, priority, PageRequest.of(page, size));
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get test cases by id")
    public Optional<TestCase> getTestCaseById(@PathVariable String id) {
        return service.getTestCaseById(id);
    }

    @PostMapping
    @Operation(summary = "Create a new test case")
    public TestCase createTestCase(@RequestBody TestCase testCase) {
        return service.createTestCase(testCase);
    }

    @PutMapping("/{id}")
    @Operation(summary = "update test case by id")
    public TestCase updateTestCase(@PathVariable String id, @RequestBody TestCase updatedTestCase) {
        return service.updateTestCase(id, updatedTestCase);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete a test case by ID")
    public ResponseEntity<String> deleteTestCase(@PathVariable String id) {
        repository.deleteById(id);
        return ResponseEntity.ok("Deleted successfully");
    }
}
