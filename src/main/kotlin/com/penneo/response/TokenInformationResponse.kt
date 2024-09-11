package com.penneo.response

data class TokenInformationResponse(
    val email: String,
    val name: String,
    val age: Int,
    val married: Boolean
)