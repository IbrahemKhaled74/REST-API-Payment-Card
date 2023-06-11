package com.vodafone.exception;

import com.vodafone.models.ErrorMessage;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.ext.ExceptionMapper;
import jakarta.ws.rs.ext.Provider;

@Provider
public class CardNumberNotFoundExceptionMapper implements ExceptionMapper<CardNumberNotFoundException> {
    @Override
    public Response toResponse(CardNumberNotFoundException e) {
        ErrorMessage errorMessage = new ErrorMessage(e.getMessage(), 404);
        return Response.status(Response.Status.NOT_FOUND)
                .entity(errorMessage)
                .build();
    }
}
