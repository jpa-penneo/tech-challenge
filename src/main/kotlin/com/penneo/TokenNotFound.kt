package com.penneo

class TokenNotFound(id: Long): RuntimeException("Token not found with id: $id")