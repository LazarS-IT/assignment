package com.graphql.assignment;

import com.netflix.graphql.dgs.DgsQueryExecutor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class GraphQlAssignmentApplicationTests {

    @Autowired
    public DgsQueryExecutor dgsQueryExecutor;


}
