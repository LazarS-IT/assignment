package com.graphql.assignment.resolver;

import com.graphql.assignment.exception.EntityNotFoundException;
import com.graphql.assignment.model.CreateUserInput;
import com.graphql.assignment.model.DeleteUserStatus;
import com.graphql.assignment.model.UpdateUserInput;
import com.graphql.assignment.model.User;
import com.graphql.assignment.repository.UserRepository;
import com.netflix.graphql.dgs.DgsComponent;
import com.netflix.graphql.dgs.DgsMutation;
import com.netflix.graphql.dgs.InputArgument;
import org.springframework.http.HttpStatus;

@DgsComponent
public class UserResolver {

    private final UserRepository userRepository;

    public UserResolver(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @DgsMutation
    public User createUser(@InputArgument CreateUserInput input) {
        User user = User.builder()
                .name(input.getName())
                .email(input.getEmail())
                .build();
        return userRepository.save(user);
    }

    @DgsMutation
    public User updateUser(@InputArgument String id, @InputArgument UpdateUserInput input) {
        User user = userRepository.findById(Long.parseLong(id))
                .orElseThrow(() -> new EntityNotFoundException(String.format("User with id: %s does not exist", id), HttpStatus.NOT_FOUND.toString()));

        if (input.getName() != null) {
            user.setName(input.getName());
        }

        if (input.getEmail() != null) {
            user.setEmail(input.getEmail());
        }

        return userRepository.save(user);
    }

    @DgsMutation
    public DeleteUserStatus deleteUser(@InputArgument String id) {
        User user = userRepository.findById(Long.parseLong(id))
                .orElseThrow(() -> new EntityNotFoundException(String.format("User with id: %s does not exist", id), HttpStatus.NOT_FOUND.toString()));

        userRepository.delete(user);
        return DeleteUserStatus.SUCCESS;
    }
}
