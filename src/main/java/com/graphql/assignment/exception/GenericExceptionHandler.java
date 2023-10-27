package com.graphql.assignment.exception;

import com.netflix.graphql.types.errors.ErrorType;
import com.netflix.graphql.types.errors.TypedGraphQLError;
import graphql.GraphQLError;
import graphql.execution.DataFetcherExceptionHandler;
import graphql.execution.DataFetcherExceptionHandlerParameters;
import graphql.execution.DataFetcherExceptionHandlerResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.CompletableFuture;

@Slf4j
@Component
public class GenericExceptionHandler implements DataFetcherExceptionHandler {
    @Override
    public CompletableFuture<DataFetcherExceptionHandlerResult> handleException(DataFetcherExceptionHandlerParameters handlerParameters) {
        if (handlerParameters.getException() instanceof EntityNotFoundException) {
            Map<String, Object> debugInfo = new HashMap<>();
            debugInfo.put("error", ((EntityNotFoundException) handlerParameters.getException()).getValidationError());

            GraphQLError graphqlError = TypedGraphQLError.newInternalErrorBuilder()
                    .message("Entity not found.")
                    .debugInfo(debugInfo)
                    .errorType(ErrorType.NOT_FOUND)
                    .path(handlerParameters.getPath()).build();

            DataFetcherExceptionHandlerResult result = DataFetcherExceptionHandlerResult.newResult()
                    .error(graphqlError)
                    .build();

            return CompletableFuture.completedFuture(result);
        } else {
            return DataFetcherExceptionHandler.super.handleException(handlerParameters);
        }
    }
}
