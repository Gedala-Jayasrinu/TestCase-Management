package com.example.TestCaseManagement.service;
import com.example.TestCaseManagement.model.TestCase;
import com.example.TestCaseManagement.repository.TestCaseRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Optional;

@Service
public class TestCaseService {
    private final TestCaseRepository repository;

    public TestCaseService(TestCaseRepository repository) {
        this.repository = repository;
    }

    public Page<TestCase> getFilteredTestCases(String status, String priority, Pageable pageable) {
        if (status != null && priority != null) {
            return repository.findByStatusAndPriority(status, priority, pageable);
        } else if (status != null) {
            return repository.findByStatus(status, pageable);
        } else if (priority != null) {
            return repository.findByPriority(priority, pageable);
        } else {
            return repository.findAll(pageable);
        }
    }

    public Optional<TestCase> getTestCaseById(String id) {
        return repository.findById(id);
    }

    public TestCase createTestCase(TestCase testCase) {
        testCase.setCreatedAt(new Date());
        testCase.setUpdatedAt(new Date());
        return repository.save(testCase);
    }

    public TestCase updateTestCase(String id, TestCase updatedTestCase) {
        return repository.findById(id).map(existingTestCase -> {
            existingTestCase.setTitle(updatedTestCase.getTitle());
            existingTestCase.setDescription(updatedTestCase.getDescription());
            existingTestCase.setStatus(updatedTestCase.getStatus());
            existingTestCase.setPriority(updatedTestCase.getPriority());
            existingTestCase.setUpdatedAt(new Date());
            return repository.save(existingTestCase);
        }).orElseThrow(() -> new RuntimeException("TestCase not found"));
    }

    public void deleteTestCase(String id) {
        repository.deleteById(id);
    }
}
