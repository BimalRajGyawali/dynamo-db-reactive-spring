package com.personal.dynamodbreactivespring.controllers

import com.personal.dynamodbreactivespring.repo.TokenRepo
import com.personal.dynamodbreactivespring.tables.Token
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RestController
import reactor.core.publisher.Mono
import java.util.*

@RestController
//Repo is injected into Controller only for Demo. Make Service Layer
class TokenController(private val tokenRepo: TokenRepo) {

    @GetMapping("/save")
    fun saveToken(): Mono<Token>{
      val token = Token()
      token.key = "1"
      token.value = UUID.randomUUID().toString()

      return tokenRepo.save(token)
    }

    @GetMapping("/get/{key}")
    fun get(@PathVariable key: String): Mono<Token>{
        return tokenRepo.get(key)
    }
}