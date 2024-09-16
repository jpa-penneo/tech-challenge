package com.penneo

class PersonNotFound(id: Int): RuntimeException("Person not found with id: $id")