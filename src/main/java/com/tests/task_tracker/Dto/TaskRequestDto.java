package com.tests.task_tracker.Dto;

import lombok.Data;

@Data
public class TaskRequestDto {
    private String title;
    private String description;
    private String status;
}
