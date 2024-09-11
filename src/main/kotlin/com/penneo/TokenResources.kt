package com.penneo

import com.penneo.entity.Token
import com.penneo.input.PersonInput
import com.penneo.input.TokenInput
import com.penneo.repository.TokenRepository
import com.penneo.response.TokenInformationResponse
import jakarta.transaction.Transactional
import jakarta.ws.rs.Consumes
import jakarta.ws.rs.GET
import jakarta.ws.rs.POST
import jakarta.ws.rs.Path
import jakarta.ws.rs.PathParam
import jakarta.ws.rs.Produces
import jakarta.ws.rs.core.MediaType
import jakarta.ws.rs.core.Response

@Path("/tokens")
class TokenResources(private val tokenRepository: TokenRepository) {
    /**
     * Task #1: Retrieves the list of email tokens
     */
    @GET
    @Path("/emails")
    @Produces(MediaType.APPLICATION_JSON)
    fun getEmails(): List<Token> = tokenRepository.listAll()

    /**
     * Task #2: Retrieves information of a token
     */
    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    fun getTokenById(@PathParam("id") id: Long): TokenInformationResponse {
        // Here you should get the token by id, decode it using JwtToken, to then return all the information: email, name, age, married
        return TokenInformationResponse(
            "some@email",
            "Some name",
            28,
            false
        )
    }

    /**
     * Task #3: Save tokens
     */
    @POST
    @Transactional
    @Consumes(MediaType.APPLICATION_JSON)
    fun saveToken(tokenInput: TokenInput): Response {
        // TokenInput contains the properties you need for the task #3.
        // Here you need to encode the token to save it using the repository
        return Response.status(Response.Status.CREATED).build()
    }


    /**
     * Task #4: Save tokens, getting information from an external resource
     */
    @POST
    @Path("/person")
    @Transactional
    @Consumes(MediaType.APPLICATION_JSON)
    fun saveToken(personInput: PersonInput): Response {
        // PersonInput contains the properties you need for the task #3.
        // Here you need to call the external API to get the data to then
        // generate and save a token, the same way you did in task # 3
        return Response.status(Response.Status.CREATED).build()
    }
}