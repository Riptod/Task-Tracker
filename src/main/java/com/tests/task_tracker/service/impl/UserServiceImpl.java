package com.tests.task_tracker.service.impl;

import java.util.List;
import java.util.Optional;

import com.tests.task_tracker.entity.User;
import com.tests.task_tracker.exception.RequestCustomException;
import com.tests.task_tracker.repositories.UserRepository;
import com.tests.task_tracker.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public User save(User user) {
        return userRepository.save(user);
    }

    @Override
    public void deleteUser(long id) {
        userRepository.deleteById(id);
    }

    @Override
    public User findUserByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    @Override
    public User getById(Long id) {
        Optional<User> user = userRepository.findById(id);
        if (user.isPresent()) {
            return user.get();
        }
        throw new RequestCustomException("User not found");
    }

    @Override
    public List<User> findAllUsers(Pageable pageable) {
        return userRepository.findAll(pageable).getContent();
    }

    @Override
    public void updateFirstName(String value, Long id) {
        userRepository.updateFirstName(value, id);
    }

    @Override
    public void updateLastName(String value, Long id) {
        userRepository.updateLastName(value, id);
    }
}
