package com.vodafone.exception;

import com.vodafone.models.ErrorMessage;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.ext.ExceptionMapper;
import jakarta.ws.rs.ext.Provider;

@Provider

public class InsufficientBalanceExceptionMapper implements ExceptionMapper<InsufficientBalanceException> {
    @Override
    public Response toResponse(InsufficientBalanceException e) {
        ErrorMessage errorMessage = new ErrorMessage(e.getMessage(), 400);
        return Response.status(Response.Status.BAD_REQUEST)
                .entity(errorMessage)
                .build();
    }
}
