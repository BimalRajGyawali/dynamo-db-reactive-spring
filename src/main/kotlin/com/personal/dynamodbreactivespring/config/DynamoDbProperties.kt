package com.personal.dynamodbreactivespring.config

import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.PropertySource
import org.springframework.stereotype.Component

@Component
@PropertySource("classpath:dynamodb.properties")
class DynamoDbProperties {

    @Value("\${aws.dynamodb.service-endpoint}")
    lateinit var serviceEndpoint: String

    @Value("\${aws.dynamodb.region}")
    lateinit var region: String

    @Value("\${aws.dynamodb.access-key}")
    lateinit var accessKey: String

    @Value("\${aws.dynamodb.secret-key}")
    lateinit var secretKey: String
}