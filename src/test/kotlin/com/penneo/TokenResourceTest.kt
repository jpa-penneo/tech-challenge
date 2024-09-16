package com.penneo

import io.quarkus.test.junit.QuarkusTest
import io.restassured.RestAssured.given
import org.hamcrest.Matchers.contains
import org.junit.jupiter.api.Test

@QuarkusTest
class TokenResourceTest {

    // TODO will break with more data
    @Test
    fun whenEmailEndpointCalled_returnsAllEmails() {
        given()
            .`when`().get("/tokens/emails")
            .then()
            .statusCode(200)
            .body("", contains("some@peneo.com"))
    }

}