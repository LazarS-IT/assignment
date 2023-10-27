package com.graphql.assignment.fetcher;

import com.graphql.assignment.GraphQlAssignmentApplicationTests;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.security.test.context.support.WithMockUser;

import java.util.List;
import java.util.Map;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class UserFetcherTest extends GraphQlAssignmentApplicationTests {

    @Test
    @Order(0)
    void createUser() {
        String mutation = "mutation createUser { createUser(input: {name: \"Lazar\", email: \"expbusinessit@gmail.com\"}) {id, name, email}}";

        String response = dgsQueryExecutor.executeAndExtractJsonPath(mutation, "data.createUser.name");

        Assertions.assertThat(response).contains("Lazar");
    }

    @Test
    @Order(1)
    void getUser() {
        String response = dgsQueryExecutor.executeAndExtractJsonPath("query user { user(id: 1) {id}}", "data.user.id");

        Assertions.assertThat(response).contains("1");
    }

    @Test
    @Order(2)
    @WithMockUser(roles = "ADMIN")
    void getUsers() {
        List<String> response = dgsQueryExecutor.executeAndExtractJsonPath("query users { users {id, name, email}}", "data.users[*].email");

        Assertions.assertThat(response.get(0)).contains("expbusinessit@gmail.com");
    }

    @Test
    @Order(3)
    void updateUser() {
        String mutation = "mutation updateUser { updateUser (id: 1, input: {name: \"Lazar\", email: \"changed@gmail.com\"}) {id, name, email}}";

        Map<String, String> response = dgsQueryExecutor.executeAndExtractJsonPath(mutation, "data.updateUser");

        Assertions.assertThat(response.get("name")).contains("Lazar");
        Assertions.assertThat(response.get("email")).contains("changed@gmail.com");
        Assertions.assertThat(response.get("id")).contains("1");
    }
}
