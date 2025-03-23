package com.example.TestCaseManagement.service;

import com.example.TestCaseManagement.model.TestCase;
import com.example.TestCaseManagement.repository.TestCaseRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class TestCaseServiceTest {

    @Mock
    private TestCaseRepository testCaseRepository;

    @InjectMocks
    private TestCaseService testCaseService;

    private TestCase testCase;

    @BeforeEach
    void setUp() {
        testCase = new TestCase();
        testCase.setId("1"); // Use String ID for MongoDB
        testCase.setTitle("Test Title");
        testCase.setDescription("Test Description");
    }

    @Test
    void shouldCreateTestCase() {
        // Mock repository behavior
        when(testCaseRepository.save(any(TestCase.class))).thenReturn(testCase);

        // Call service method
        TestCase createdTestCase = testCaseService.createTestCase(testCase);

        // Verify repository interactions
        verify(testCaseRepository, times(1)).save(testCase);

        // Assert results
        assertThat(createdTestCase).isNotNull();
        assertThat(createdTestCase.getTitle()).isEqualTo("Test Title");
    }

    @Test
    void shouldFindTestCaseById() {
        // Mock repository behavior
        when(testCaseRepository.findById("1")).thenReturn(Optional.of(testCase));

        // Call service method
        Optional<TestCase> foundTestCase = testCaseService.getTestCaseById("1");

        // Verify repository interactions
        verify(testCaseRepository, times(1)).findById("1");

        // Assert results
        assertThat(foundTestCase).isPresent();
        assertThat(foundTestCase.get().getTitle()).isEqualTo("Test Title");
    }

    @Test
    void shouldDeleteTestCase() {
        // Call service method
        testCaseService.deleteTestCase("1");

        // Verify repository interactions
        verify(testCaseRepository, times(1)).deleteById("1");
    }
}
