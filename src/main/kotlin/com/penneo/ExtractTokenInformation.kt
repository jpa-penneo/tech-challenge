package com.penneo

import com.auth0.jwt.interfaces.DecodedJWT
import com.fasterxml.jackson.core.type.TypeReference
import com.fasterxml.jackson.databind.ObjectMapper
import com.penneo.entity.Token
import com.penneo.jwt.JwtToken
import com.penneo.response.TokenInformationResponse
import java.util.*

class ExtractTokenInformation {
    companion object {
        private val objectMapper = ObjectMapper()

        fun from(token: Token): TokenInformationResponse {
            val decodedToken = JwtToken.decodeToken(token.token)
            val payload = payloadOf(decodedToken)
            assertPayload(payload)
            return TokenInformationResponse(
                token.email,
                payload["name"] as String,
                payload["age"] as Int,
                payload["married"] as Boolean
            )
        }

        private fun payloadOf(decodedToken: DecodedJWT?): Map<String, Any> {
            val payload = decodedToken?.payload ?: throw IllegalArgumentException("Payload not present in token!")
            val payloadJson = String(Base64.getDecoder().decode(payload))
            val type = object : TypeReference<Map<String, Any>>() {}
            return objectMapper.readValue(payloadJson, type)
        }

        private fun assertPayload(payload: Map<String, Any>) {
            if(payload["name"] == null) {
                throw IllegalArgumentException("\"name\" is not present in token!")
            }
            if(payload["age"] == null) {
                throw IllegalArgumentException("\"age\" is not present in token!")
            }
            if(payload["married"] == null) {
                throw IllegalArgumentException("\"married\" is not present in token!")
            }
        }
    }
}