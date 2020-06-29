package com.tests.task_tracker.service.impl;

import java.util.List;
import java.util.Optional;

import com.tests.task_tracker.entity.Task;
import com.tests.task_tracker.entity.User;
import com.tests.task_tracker.exception.RequestCustomException;
import com.tests.task_tracker.repositories.TaskRepository;
import com.tests.task_tracker.service.TaskService;
import com.tests.task_tracker.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

@Service
public class TaskServiceImpl implements TaskService {
    @Autowired
    private TaskRepository taskRepository;
    @Autowired
    private UserService userService;

    @Override
    public Task save(Task task) {
        return taskRepository.save(task);
    }

    @Override
    public Task changeStatus(Long taskId, String status) {
        Optional<Task> task = taskRepository.findById(taskId);
        if (task.isPresent() && (status.equals("View") || status.equals("In Progress")
                || status.equals("Done"))) {
            task.get().setStatus(status);
            save(task.get());
            return task.get();
        }
        throw new RequestCustomException("Invalid task status");
    }

    @Override
    public void delete(Long taskId) {
        taskRepository.deleteById(taskId);
    }

    @Override
    public void changeUserForTask(Long taskId, String email, Authentication authentication) {
        User user = userService.findUserByEmail(email);
        Optional<Task> task = taskRepository.findById(taskId);
        if (task.isPresent()) {
            User currentUser = userService.findUserByEmail(authentication.getName());
            currentUser.getTasks().remove(task.get());
            user.getTasks().add(task.get());
            userService.save(user);
            userService.save(currentUser);
        }
    }

    @Override
    public void updateTitle(String value, Long id) {
        taskRepository.updateTitle(value, id);
    }

    @Override
    public void updateDescription(String value, Long id) {
        taskRepository.updateDescription(value, id);
    }

    @Override
    public List<Task> filterByStatusAsc(String value) {
        return taskRepository.filterByStatusAsc(value);
    }

    @Override
    public List<Task> filterByStatusDesc(String value) {
        return taskRepository.filterByStatusDesc(value);
    }
}
