package com.personal.dynamodbreactivespring.repo

import com.personal.dynamodbreactivespring.tables.Token
import reactor.core.publisher.Mono

interface TokenRepo {

    fun save(token: Token): Mono<Token>

    fun get(key: String): Mono<Token>

}