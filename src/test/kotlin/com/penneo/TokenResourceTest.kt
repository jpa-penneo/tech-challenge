package com.penneo

import io.quarkus.test.junit.QuarkusTest
import io.restassured.RestAssured.given
import org.hamcrest.CoreMatchers.`is`
import org.hamcrest.Matchers.hasItem
import org.junit.jupiter.api.Test

private const val EMAIL_GET_PATH = "/tokens/emails"
private const val TOKEN_GET_PATH = "/tokens/{id}"
private const val TOKEN_ADD_PATH = "/tokens/"

@QuarkusTest
class TokenResourceTest {

    // TODO needs to have test only DB, data gets added by other tests
    @Test
    fun getEmails_whenCalled_returnsAllEmails() {
        given()
            .`when`().get(EMAIL_GET_PATH)
            .then().statusCode(200)
            .body("", hasItem("some@peneo.com"))
    }

    @Test
    fun getToken_whenValidTokenId_returnsDecodedTokenInformation() {
        given()
            .`when`().get(TOKEN_GET_PATH, 1)
            .then().statusCode(200)
            .body("email", `is`("some@peneo.com"))
            .body("name", `is`("Penneo Employee"))
            .body("age", `is`(30))
            .body("married", `is`(true))
    }

    @Test
    fun getToken_whenInvalidTokenId_returns404NotFound() {
        given()
            .`when`().get(TOKEN_GET_PATH, 9999)
            .then().statusCode(404)
            .body(`is`("Token not found with id: 9999"))
    }

    @Test
    fun addToken_whenValidRequest_returns201Created() {
        val request = """
            {
                "email": "johndoe@example.com",
                "name": "John Doe",
                "age": 24,
                "married": false
            }
        """.trimIndent()
        given()
            .contentType("application/json")
            .body(request)
            .`when`().post(TOKEN_ADD_PATH)
            .then().statusCode(201)
    }

    @Test
    fun addToken_whenMissingFields_returns400BadRequest() {
        val request = """
            {
                "email": "missingAgeAndMarried@example.com",
                "name": "John Doe",
            }
        """.trimIndent()
        given()
            .contentType("application/json")
            .body(request)
            .`when`().post(TOKEN_ADD_PATH)
            .then().statusCode(400)
    }

}