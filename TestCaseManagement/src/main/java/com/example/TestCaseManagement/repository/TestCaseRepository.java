package com.example.TestCaseManagement.repository;

import com.example.TestCaseManagement.model.TestCase;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TestCaseRepository extends MongoRepository<TestCase, String> {
    Page<TestCase> findByStatusAndPriority(String status, String priority, Pageable pageable);
    Page<TestCase> findByStatus(String status, Pageable pageable);
    Page<TestCase> findByPriority(String priority, Pageable pageable);
}
