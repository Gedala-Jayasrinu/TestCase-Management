package com.example.TestCaseManagement.dto;


import com.example.TestCaseManagement.model.Priority;
import com.example.TestCaseManagement.model.Status;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TestCaseRequestDTO {

    @NotBlank(message = "Title is required")
    private String title;

    private String description;

    private Status status;

    private Priority priority;
}