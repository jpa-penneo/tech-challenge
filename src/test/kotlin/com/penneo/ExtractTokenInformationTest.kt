package com.penneo

import com.penneo.entity.Token
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows

class ExtractTokenInformationTest {
    @Test
    fun from_whenEmptyPayload_thenThrowsIllegalArgument() {
        val token = Token(
            id = 1,
            email = "test@example.com",
            token = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.e30.Et9HFtf9R3GEMA0IICOfFMVXY7kkTX1wr4qCyhIf58U"
        )

        assertThrows<IllegalArgumentException> {
            ExtractTokenInformation.from(token)
        }
    }
}