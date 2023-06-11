package com.vodafone.exception;

import com.vodafone.models.ErrorMessage;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.ext.ExceptionMapper;
import jakarta.ws.rs.ext.Provider;

@Provider

public class InvalidCardNumberExceptionMapper implements ExceptionMapper<InvalidCardNumberException> {
    @Override
    public Response toResponse(InvalidCardNumberException e) {
        ErrorMessage errorMessage = new ErrorMessage(e.getMessage(), 403);
        return Response.status(Response.Status.FORBIDDEN)
                .entity(errorMessage)
                .build();
    }
}
