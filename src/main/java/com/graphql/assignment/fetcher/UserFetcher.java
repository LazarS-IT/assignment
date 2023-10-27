package com.graphql.assignment.fetcher;

import com.graphql.assignment.model.CreateUserInput;
import com.graphql.assignment.model.DeleteUserStatus;
import com.graphql.assignment.model.UpdateUserInput;
import com.graphql.assignment.model.User;
import com.graphql.assignment.service.UserService;
import com.netflix.graphql.dgs.DgsComponent;
import com.netflix.graphql.dgs.DgsMutation;
import com.netflix.graphql.dgs.DgsQuery;
import com.netflix.graphql.dgs.InputArgument;
import org.springframework.security.access.prepost.PreAuthorize;

import java.util.List;

@DgsComponent
public class UserFetcher {

    private final UserService userService;

    public UserFetcher(UserService userService) {
        this.userService = userService;
    }

    @DgsQuery(field = "users")
    @PreAuthorize("hasRole('ADMIN')")
    public List<User> getAllUsers() {
        return userService.getAllUsers();
    }

    @DgsQuery(field = "user")
    public User getUserById(@InputArgument String id) {
        return userService.getUserById(Long.parseLong(id));
    }

    @DgsMutation
    public User createUser(@InputArgument CreateUserInput input) {
        return userService.createUser(input);
    }

    @DgsMutation
    public User updateUser(@InputArgument String id, @InputArgument UpdateUserInput input) {
        return userService.updateUser(Long.parseLong(id), input);
    }

    @DgsMutation
    public DeleteUserStatus deleteUser(@InputArgument String id) {
        return userService.deleteUser(Long.valueOf(id));
    }
}
