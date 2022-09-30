package com.personal.dynamodbreactivespring

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class DynamoDbReactiveSpringApplication

fun main(args: Array<String>) {
	runApplication<DynamoDbReactiveSpringApplication>(*args)
}
