package com.graphql.assignment.service;

import com.graphql.assignment.model.CreateUserInput;
import com.graphql.assignment.model.DeleteUserStatus;
import com.graphql.assignment.model.UpdateUserInput;
import com.graphql.assignment.model.User;

import java.util.List;

public interface UserService {
    User getUserById(Long id);

    List<User> getAllUsers();

    User updateUser(Long id, UpdateUserInput userInput);

    DeleteUserStatus deleteUser(Long id);

    User createUser(CreateUserInput userInput);
}
