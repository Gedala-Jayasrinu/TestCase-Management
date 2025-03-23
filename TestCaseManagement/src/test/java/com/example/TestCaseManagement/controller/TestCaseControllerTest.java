package com.example.TestCaseManagement.controller;

import com.example.TestCaseManagement.model.TestCase;
import com.example.TestCaseManagement.service.TestCaseService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Arrays;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith(MockitoExtension.class)
class TestCaseControllerTest {

    private MockMvc mockMvc;

    @Mock
    private TestCaseService testCaseService;

    @InjectMocks
    private TestCaseController testCaseController;

    private TestCase testCase;

    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(testCaseController).build();

        testCase = new TestCase();
        testCase.setId("1");
        testCase.setTitle("Test Case 1");
        testCase.setDescription("Test Description");
    }

    @Test
    void shouldCreateTestCase() throws Exception {
        when(testCaseService.createTestCase(any(TestCase.class))).thenReturn(testCase);

        mockMvc.perform(post("/api/testcases")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(testCase)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.title").value("Test Case 1"));

        verify(testCaseService, times(1)).createTestCase(any(TestCase.class));
    }

//    @Test
//    void shouldGetAllTestCases() throws Exception {
//        when(testCaseService.getAllTestCases()).thenReturn(Arrays.asList(testCase));
//
//        mockMvc.perform(get("/api/testcases"))
//                .andExpect(status().isOk())
//                .andExpect(jsonPath("$[0].title").value("Test Case 1"));
//
//        verify(testCaseService, times(1)).getAllTestCases();
//    }

    @Test
    void shouldGetTestCaseById() throws Exception {
        when(testCaseService.getTestCaseById("1")).thenReturn(Optional.of(testCase));

        mockMvc.perform(get("/api/testcases/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.title").value("Test Case 1"));

        verify(testCaseService, times(1)).getTestCaseById("1");
    }

    @Test
    void shouldReturnNotFoundForInvalidTestCaseId() throws Exception {
        when(testCaseService.getTestCaseById("999")).thenReturn(Optional.empty());

        mockMvc.perform(get("/api/testcases/999"))
                .andExpect(status().isNotFound());

        verify(testCaseService, times(1)).getTestCaseById("999");
    }

    @Test
    void shouldDeleteTestCase() throws Exception {
        doNothing().when(testCaseService).deleteTestCase("1");

        mockMvc.perform(delete("/api/testcases/1"))
                .andExpect(status().isNoContent());

        verify(testCaseService, times(1)).deleteTestCase("1");
    }
}
