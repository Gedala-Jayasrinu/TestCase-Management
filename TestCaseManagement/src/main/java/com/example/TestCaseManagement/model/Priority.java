package com.example.TestCaseManagement.model;

import com.fasterxml.jackson.annotation.JsonCreator;

public enum Priority {
    LOW, MEDIUM, HIGH;

    @JsonCreator
    public static Priority fromString(String value) {
        return switch (value.toUpperCase()) {
            case "HIGH" -> HIGH;
            case "LOW" -> LOW;
            case "MEDIUM" -> MEDIUM;
            default -> throw new IllegalArgumentException("Invalid priority: " + value);
        };
    }
}