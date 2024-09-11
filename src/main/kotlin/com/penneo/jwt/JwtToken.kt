package com.penneo.jwt

import com.auth0.jwt.JWT
import com.auth0.jwt.algorithms.Algorithm
import com.auth0.jwt.interfaces.DecodedJWT


class JwtToken {
    companion object {
        private const val SECRET_KEY = "JBJhklkDgymB9jgielQoAuMw7u4HNlJPgZahv7hwMEG"

        fun generateToken(name: String, age: Int, married: Boolean): String {
            val algorithm = Algorithm.HMAC256(SECRET_KEY);
            return JWT.create()
                .withClaim("name", name)
                .withClaim("age", age)
                .withClaim("married", married)
                .sign(algorithm)
        }

        fun decodeToken(token: String): DecodedJWT? {
            return JWT.decode(token)
        }
    }
}