package com.personal.dynamodbreactivespring.tables

import software.amazon.awssdk.enhanced.dynamodb.mapper.annotations.DynamoDbAttribute
import software.amazon.awssdk.enhanced.dynamodb.mapper.annotations.DynamoDbBean
import software.amazon.awssdk.enhanced.dynamodb.mapper.annotations.DynamoDbPartitionKey

@DynamoDbBean
class Token {

    @get:DynamoDbPartitionKey
    @get:DynamoDbAttribute("key")
    lateinit var key: String


    @get:DynamoDbAttribute("value")
    lateinit var value: String

}