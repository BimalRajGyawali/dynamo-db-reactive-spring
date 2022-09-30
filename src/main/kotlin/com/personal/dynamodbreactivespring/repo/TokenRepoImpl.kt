package com.personal.dynamodbreactivespring.repo

import com.personal.dynamodbreactivespring.tables.Token
import org.springframework.stereotype.Repository
import reactor.core.publisher.Mono
import software.amazon.awssdk.enhanced.dynamodb.DynamoDbEnhancedAsyncClient
import software.amazon.awssdk.enhanced.dynamodb.Key
import software.amazon.awssdk.enhanced.dynamodb.TableSchema

@Repository
class TokenRepoImpl(enhancedAsyncClient: DynamoDbEnhancedAsyncClient) : TokenRepo {

    private val tokenAsyncTable = enhancedAsyncClient.table(
        Token::class.java.simpleName,
        TableSchema.fromBean(Token::class.java)
    )

    override fun save(token: Token): Mono<Token> {
        return Mono.fromFuture(tokenAsyncTable.putItem(token))
            .thenReturn(token)
    }

    override fun get(key: String): Mono<Token> {
        return Mono.fromFuture(
            tokenAsyncTable.getItem(
                Key.builder()
                    .partitionValue(key).build()
            )
        )
    }
}

