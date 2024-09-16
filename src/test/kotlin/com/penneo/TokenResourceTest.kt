package com.penneo

import io.quarkus.test.junit.QuarkusTest
import io.restassured.RestAssured.given
import org.hamcrest.Matchers.contains
import org.hamcrest.CoreMatchers.`is`
import org.junit.jupiter.api.Test

private const val EMAIL_GET_PATH = "/tokens/emails"
private const val TOKEN_GET_PATH = "/tokens/{id}"
private const val TOKEN_ADD_PATH = "/tokens/"

@QuarkusTest
class TokenResourceTest {

    // TODO will break with more data
    @Test
    fun getEmails_whenCalled_returnsAllEmails() {
        given()
            .`when`().get(EMAIL_GET_PATH)
            .then().statusCode(200)
            .body("", contains("some@peneo.com"))
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

}