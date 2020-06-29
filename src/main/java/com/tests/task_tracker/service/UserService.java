package com.tests.task_tracker.service;

import java.util.List;

import com.tests.task_tracker.entity.User;
import org.springframework.data.domain.Pageable;

public interface UserService {

    User save(User user);

    void deleteUser(long id);

    User findUserByEmail(String email);

    User getById(Long id);

    List<User> findAllUsers(Pageable pageable);

    void updateFirstName(String value, Long id);

    void updateLastName(String value, Long id);
}
