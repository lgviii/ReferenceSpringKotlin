package com.reference.testing

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.context.annotation.ComponentScan
import org.springframework.context.annotation.Configuration

@SpringBootApplication
class TestingApplication

fun main(args: Array<String>) {
	runApplication<TestingApplication>(*args)
}
