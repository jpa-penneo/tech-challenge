package com.penneo.repository

import com.penneo.entity.Token
import io.quarkus.hibernate.orm.panache.kotlin.PanacheRepository
import jakarta.enterprise.context.ApplicationScoped

@ApplicationScoped
class TokenRepository : PanacheRepository<Token> {
    fun findAllEmails(): List<String> {
        return find("select email from Token")
            .project(String::class.java).list()
    }
}