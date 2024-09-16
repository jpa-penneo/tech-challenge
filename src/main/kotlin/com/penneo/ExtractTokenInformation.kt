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
            // TODO check if there is more prettier way to do this
            val type = object : TypeReference<Map<String, Any>>() {}
            return objectMapper.readValue(payloadJson, type)
        }
    }

}