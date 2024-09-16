package com.penneo.exception

class PersonNotFound(id: Int): NotFound("Person not found with id: $id")