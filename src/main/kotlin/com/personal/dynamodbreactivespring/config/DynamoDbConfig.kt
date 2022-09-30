package com.personal.dynamodbreactivespring.config

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import software.amazon.awssdk.auth.credentials.AwsBasicCredentials
import software.amazon.awssdk.auth.credentials.StaticCredentialsProvider
import software.amazon.awssdk.enhanced.dynamodb.DynamoDbEnhancedAsyncClient
import software.amazon.awssdk.services.dynamodb.DynamoDbAsyncClient
import java.net.URI


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