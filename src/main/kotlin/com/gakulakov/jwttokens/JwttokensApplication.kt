package com.gakulakov.jwttokens

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.context.properties.ConfigurationPropertiesScan
import org.springframework.boot.runApplication

@ConfigurationPropertiesScan
@SpringBootApplication
class JwttokensApplication

fun main(args: Array<String>) {
    runApplication<JwttokensApplication>(*args)
}
