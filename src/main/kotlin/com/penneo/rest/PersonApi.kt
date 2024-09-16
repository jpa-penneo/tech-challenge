package com.penneo.rest

import jakarta.enterprise.context.ApplicationScoped
import jakarta.ws.rs.WebApplicationException
import org.eclipse.microprofile.rest.client.inject.RestClient

@ApplicationScoped
class PersonApi(
    @RestClient private val personClient: PersonApiClient
) {
    fun getUserById(id: Int): Person? {
        return try {
            personClient.getUserById(id)
        } catch (e: WebApplicationException) {
            if (e.response.status == 404) {
                null
            } else {
                throw e
            }
        }
    }

}