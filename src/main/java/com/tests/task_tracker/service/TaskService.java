package com.tests.task_tracker.service;

import java.util.List;

import com.tests.task_tracker.entity.Task;
import org.springframework.security.core.Authentication;

public interface TaskService {
    Task save(Task task);

    Task changeStatus(Long taskId, String status);

    void delete(Long taskId);

    void changeUserForTask(Long taskId, String email, Authentication authentication);

    void updateTitle(String value, Long id);

    void updateDescription(String value, Long id);

    List<Task> filterByStatusAsc(String value);

    List<Task> filterByStatusDesc(String value);
}
