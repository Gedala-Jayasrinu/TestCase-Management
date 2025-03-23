package com.example.TestCaseManagement.dto;


import com.example.TestCaseManagement.model.Priority;
import com.example.TestCaseManagement.model.Status;
import lombok.*;

import java.util.Date;

@Getter @Setter @NoArgsConstructor @AllArgsConstructor
public class TestCaseResponseDTO {
    private String id;
    private String title;
    private String description;
    private Status status;
    private Priority priority;
    private Date createdAt;
    private Date updatedAt;
}