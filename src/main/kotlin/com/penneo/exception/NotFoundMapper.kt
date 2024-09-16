package com.penneo.exception

import jakarta.ws.rs.core.Response
import jakarta.ws.rs.ext.ExceptionMapper
import jakarta.ws.rs.ext.Provider

@Provider
class NotFoundMapper : ExceptionMapper<NotFound> {
    override fun toResponse(exception: NotFound): Response {
        val response = NotFoundResponse(exception.message ?: "Object not found")
        return Response.status(Response.Status.NOT_FOUND)
            .entity(response)
            .build()
    }
}
