package com.tests.task_tracker.Controller;

import java.util.ArrayList;
import javax.annotation.PostConstruct;

import com.tests.task_tracker.entity.Task;
import com.tests.task_tracker.entity.User;
import com.tests.task_tracker.service.TaskService;
import com.tests.task_tracker.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;

@Controller
public class InjectController {
    @Autowired
    private UserService userService;
    @Autowired
    private TaskService taskService;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @PostConstruct
    public void fillDatabase() {
        User user1 = new User(1L, "Bob", "Martin", "bob@gmail.com", passwordEncoder.encode("1234"), new ArrayList<>());
        User user2 = new User(2L, "Sam", "Martin", "sam@gmail.com", passwordEncoder.encode("4567"), new ArrayList<>());
        User user3 = new User(3L, "Rick", "Martin", "rick@gmail.com", passwordEncoder.encode("8910"), new ArrayList<>());

        for (int i = 0; i < 45; i++) {
            User user = new User();
            user.setEmail(i + "someEmail");
            user.setFirst_name(i + "Name");
            user.setLast_name("Last name" + i);
            user.setPassword("1234");
            userService.save(user);
        }

        Task task1 = new Task(1L, "Database sorting", null, "In Progress");
        Task task2 = new Task(2L, "Database sorting", null, "View");
        Task task3 = new Task(3L, "Angular", null, "Done");
        Task task4 = new Task(4L, "Angular", null, "In Progress");
        Task task5 = new Task(5L, "Dev-Ops", null, "View");

        taskService.save(task1);
        taskService.save(task2);
        taskService.save(task3);
        taskService.save(task4);
        taskService.save(task5);

        user1.getTasks().add(task1);
        user1.getTasks().add(task2);
        user2.getTasks().add(task3);
        user2.getTasks().add(task4);
        user3.getTasks().add(task5);

        userService.save(user1);
        userService.save(user2);
        userService.save(user3);
    }
}
