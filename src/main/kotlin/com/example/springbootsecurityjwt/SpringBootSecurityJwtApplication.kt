package com.example.springbootsecurityjwt

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.context.properties.ConfigurationPropertiesScan
import org.springframework.boot.runApplication

@SpringBootApplication
@ConfigurationPropertiesScan("com.example.springbootsecurityjwt.properties")
class SpringBootSecurityJwtApplication

fun main(args: Array<String>) {
    runApplication<SpringBootSecurityJwtApplication>(*args)
}
