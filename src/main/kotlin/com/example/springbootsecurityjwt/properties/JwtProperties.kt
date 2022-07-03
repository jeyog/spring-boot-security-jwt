package com.example.springbootsecurityjwt.properties

import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.boot.context.properties.ConstructorBinding

@ConstructorBinding
@ConfigurationProperties("jwt")
class JwtProperties(
    val secretKey: String,
    val expiration: Long
) {
}