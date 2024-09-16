package com.penneo

import io.quarkus.test.junit.QuarkusTest
import io.restassured.RestAssured.given
import org.hamcrest.Matchers.contains
import org.hamcrest.CoreMatchers.`is`
import org.junit.jupiter.api.Test

@QuarkusTest
class TokenResourceTest {

    // TODO will break with more data
    @Test
    fun whenEmailEndpointCalled_returnsAllEmails() {
        given()
            .`when`().get("/tokens/emails")
            .then().statusCode(200)
            .body("", contains("some@peneo.com"))
    }

    @Test
    fun whenValidTokenId_returnsDecodedTokenInformation() {
        given()
            .`when`().get("/tokens/1")
            .then().statusCode(200)
            .body("email", `is`("some@peneo.com"))
            .body("name", `is`("Penneo Employee"))
            .body("age", `is`(30))
            .body("married", `is`(true))
    }

    @Test
    fun whenInvalidTokenId_returns404() {
        given()
            .`when`().get("/tokens/9999")
            .then().statusCode(404)
            .body(`is`("Token not found with id: 9999"))
    }

}