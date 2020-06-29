package com.tests.task_tracker.Controller;

import java.util.List;

import com.tests.task_tracker.Dto.UserRegistrationDto;
import com.tests.task_tracker.Dto.UserUpdateDto;
import com.tests.task_tracker.entity.User;
import com.tests.task_tracker.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@ResponseBody
public class UserController {
    @Autowired
    private UserService userService;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @GetMapping(value = "/getAllUsers")
    public List<User> getAllUsers(@RequestParam Integer page) {
        Pageable pageable = PageRequest.of(page, 10);
        return userService.findAllUsers(pageable);
    }

    @PostMapping(value = "/registration")
    public void registration(@RequestBody UserRegistrationDto userRegistrationDto) {
        User user = new User();
        user.setFirst_name(userRegistrationDto.getFirst_name());
        user.setLast_name(userRegistrationDto.getLast_name());
        user.setEmail(userRegistrationDto.getEmail());
        user.setPassword(passwordEncoder.encode(userRegistrationDto.getPassword()));
        userService.save(user);
    }

    @GetMapping(value = "/deleteUser")
    public void deleteUser(@RequestParam Long id) {
        userService.deleteUser(id);
    }

    @GetMapping(value = "/getUser")
    public User getUser(@RequestParam String email) {
        return userService.findUserByEmail(email);
    }

    @PostMapping(value = "/updateFirstName")
    public void updateFirstName(@RequestBody UserUpdateDto userUpdateDto) {
        userService.updateFirstName(userUpdateDto.getValue(),
                userUpdateDto.getId());
    }

    @PostMapping(value = "/updateLastName")
    public void updateLastName(@RequestBody UserUpdateDto userUpdateDto) {
        userService.updateLastName(userUpdateDto.getValue(),
                userUpdateDto.getId());
    }
}
