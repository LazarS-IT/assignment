package com.graphql.assignment.fetcher;

import com.graphql.assignment.exception.EntityNotFoundException;
import com.graphql.assignment.model.User;
import com.graphql.assignment.repository.UserRepository;
import com.netflix.graphql.dgs.DgsComponent;
import com.netflix.graphql.dgs.DgsQuery;
import com.netflix.graphql.dgs.InputArgument;
import org.springframework.http.HttpStatus;

import java.util.List;

@DgsComponent
public class UserFetcher {

    private final UserRepository userRepository;

    public UserFetcher(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @DgsQuery(field = "users")
    public List<User> getAll() {
        return userRepository.findAll();
    }

    @DgsQuery(field = "user")
    public User getUser(@InputArgument String id) {
        return userRepository.findById(Long.parseLong(id))
                .orElseThrow(() -> new EntityNotFoundException(String.format("User with id: %s not found", id), HttpStatus.NOT_FOUND.toString()));
    }
}
