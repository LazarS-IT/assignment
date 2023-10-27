package com.graphql.assignment.service;

import com.graphql.assignment.exception.EntityNotFoundException;
import com.graphql.assignment.model.CreateUserInput;
import com.graphql.assignment.model.DeleteUserStatus;
import com.graphql.assignment.model.UpdateUserInput;
import com.graphql.assignment.model.User;
import com.graphql.assignment.repository.UserRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public User getUserById(Long id) {
        return userRepository.findById(id).orElseThrow(() -> new EntityNotFoundException(String.format("User with id: %s not found", id), String.valueOf(HttpStatus.NOT_FOUND.value())));

    }

    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public User updateUser(Long id, UpdateUserInput userInput) {
        User user = userRepository.findById(id).orElseThrow(() -> new EntityNotFoundException(String.format("User with id: %s does not exist", id), String.valueOf(HttpStatus.NOT_FOUND.value())));

        if (userInput.getName() != null) {
            user.setName(userInput.getName());
        }

        if (userInput.getEmail() != null) {
            user.setEmail(userInput.getEmail());
        }

        return userRepository.save(user);
    }

    @Override
    public DeleteUserStatus deleteUser(Long id) {
        User user = userRepository.findById(id).orElseThrow(() -> new EntityNotFoundException(String.format("User with id: %s does not exist", id), String.valueOf(HttpStatus.NOT_FOUND.value())));

        userRepository.delete(user);
        return DeleteUserStatus.SUCCESS;
    }

    @Override
    public User createUser(CreateUserInput userInput) {
        User user = User.builder().name(userInput.getName()).email(userInput.getEmail()).build();
        return userRepository.save(user);
    }
}
