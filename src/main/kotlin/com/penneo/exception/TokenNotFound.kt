package com.penneo.exception

class TokenNotFound(id: Long): NotFound("Token not found with id: $id")