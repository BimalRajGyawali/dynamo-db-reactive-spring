# dynamo-db-reactive-spring

Reactive Integration of AWS DynamoDB with Spring WebFlux and Kotlin

#### Dependency

```pom.xml
<dependency>
  <groupId>software.amazon.awssdk</groupId>
  <artifactId>dynamodb-enhanced</artifactId>
  <version>2.17.276</version>
</dependency>
    
```

#### Configuration
```Kotlin

@Configuration
class DynamoDbConfig(private val dynamoDBProperties: DynamoDbProperties) {


    @Bean
    fun awsBasicCredentials(): AwsBasicCredentials {
        return AwsBasicCredentials.create(dynamoDBProperties.accessKey, dynamoDBProperties.secretKey)
    }

    @Bean
    fun getDynamoDbAsyncClient(): DynamoDbAsyncClient {
        return DynamoDbAsyncClient.builder()
            .credentialsProvider(StaticCredentialsProvider.create(awsBasicCredentials()))
            .endpointOverride(URI.create(dynamoDBProperties.serviceEndpoint))
            .build()
    }

    @Bean
    fun getDynamoDbEnhancedAsyncClient(): DynamoDbEnhancedAsyncClient {
        return DynamoDbEnhancedAsyncClient.builder()
            .dynamoDbClient(getDynamoDbAsyncClient())
            .build()
    }
}
```

#### Repo
``` Kotlin
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
```

#### Controller
```Kotlin
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
```
