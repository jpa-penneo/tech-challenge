package com.penneo

import jakarta.ws.rs.core.Response
import jakarta.ws.rs.ext.ExceptionMapper
import jakarta.ws.rs.ext.Provider

@Provider
class TokenNotFoundMapper : ExceptionMapper<TokenNotFound> {
    override fun toResponse(exception: TokenNotFound): Response {
        // TODO if response is a JSON that error should also be a JSON
        return Response.status(Response.Status.NOT_FOUND)
            .entity(exception.message)
            .build()
    }
}

@Provider
class PersonNotFoundMapper : ExceptionMapper<PersonNotFound> {
    override fun toResponse(exception: PersonNotFound): Response {
        return Response.status(Response.Status.NOT_FOUND)
            .entity(exception.message)
            .build()
    }
}
