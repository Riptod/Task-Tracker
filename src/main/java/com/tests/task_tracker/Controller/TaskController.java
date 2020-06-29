package com.tests.task_tracker.Controller;

import java.util.List;

import com.tests.task_tracker.Dto.TaskRequestDto;
import com.tests.task_tracker.Dto.TaskUpdateDto;
import com.tests.task_tracker.entity.Task;
import com.tests.task_tracker.entity.User;
import com.tests.task_tracker.exception.RequestCustomException;
import com.tests.task_tracker.service.TaskService;
import com.tests.task_tracker.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController(value = "/task")
public class TaskController {

    @Autowired
    private TaskService taskService;
    @Autowired
    private UserService userService;

    @PostMapping(value = "createTask")
    public void createTask(@RequestBody TaskRequestDto taskRequestDto, Authentication authentication) {
        Task task = new Task();
        task.setStatus(taskRequestDto.getStatus());
        task.setDescription(taskRequestDto.getDescription());
        task.setStatus(taskRequestDto.getStatus());
        taskService.save(task);
        User user = userService.findUserByEmail(authentication.getName());
        user.getTasks().add(task);
        userService.save(user);
    }

    @PostMapping(value = "/updateTitle")
    public void updateTitle(@RequestBody TaskUpdateDto taskUpdateDto) {
        taskService.updateTitle(taskUpdateDto.getValue(), taskUpdateDto.getId());
    }

    @PostMapping(value = "/updateDescription")
    public void updateDescription(@RequestBody TaskUpdateDto taskUpdateDto) {
        taskService.updateDescription(taskUpdateDto.getValue(), taskUpdateDto.getId());
    }

    @GetMapping(value = "/changeStatus")
    public void changeStatus(@RequestParam Long taskId, @RequestParam String status) {
        taskService.changeStatus(taskId, status);
    }

    @GetMapping(value = "/delete")
    public void deleteTask(@RequestParam Long taskId) {
        taskService.delete(taskId);
    }

    @GetMapping(value = "/tasks")
    public List<Task> getFilteredTask(@RequestParam String status, @RequestParam String sorting) {
        if (sorting.equalsIgnoreCase("ASC")) {
            return taskService.filterByStatusAsc(status);
        }
        if (sorting.equalsIgnoreCase("DESC")) {
            return taskService.filterByStatusDesc(status);
        }
        throw new RequestCustomException("Invalid sorting type");
    }

    @GetMapping(value = "/changeUserForTask")
    public void changeUserForTask(@RequestParam Long taskId, @RequestParam String email, Authentication authentication) {
        taskService.changeUserForTask(taskId, email, authentication);
    }

}
