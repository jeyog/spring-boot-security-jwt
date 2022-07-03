package com.example.springbootsecurityjwt.service

import com.example.springbootsecurityjwt.entity.JwtUser
import com.example.springbootsecurityjwt.properties.JwtProperties
import io.jsonwebtoken.Claims
import io.jsonwebtoken.Jwts
import io.jsonwebtoken.SignatureAlgorithm
import io.jsonwebtoken.security.Keys
import org.springframework.stereotype.Service
import java.nio.charset.StandardCharsets
import java.sql.Timestamp
import java.time.LocalDateTime
import java.time.ZoneId
import javax.annotation.PostConstruct
import javax.crypto.SecretKey

@Service
class JwtService(
    private val jwtProperties: JwtProperties
) {
    lateinit var secretKey: SecretKey

    @PostConstruct
    fun init() {
        secretKey = Keys.hmacShaKeyFor(jwtProperties.secretKey.toByteArray(StandardCharsets.UTF_8))
    }

    fun generateToken(user: JwtUser): String {
        val claims = Jwts.claims().setSubject(user.phone)
        val now = LocalDateTime.now()
        return Jwts.builder()
            .setClaims(claims)
            .addClaims(mapOf("name" to user.name))
            .setIssuedAt(Timestamp.valueOf(now))
            .setExpiration(Timestamp.valueOf(now.plusSeconds(jwtProperties.expiration)))
            .signWith(secretKey, SignatureAlgorithm.HS256)
            .compact()
    }

    fun getJwtUser(token: String): JwtUser {
        val claims = getAllClaims(token)
        return JwtUser(claims["name"].toString(), claims.subject)
    }

    @Throws(Exception::class)
    private fun getAllClaims(token: String): Claims {
        return Jwts.parserBuilder().setSigningKey(secretKey).build().parseClaimsJws(token).body
    }

    @Throws(Exception::class)
    fun validateToken(token: String): Boolean {
        val claims = getAllClaims(token)
        val expiration = claims.expiration
        return expiration.after(Timestamp.valueOf(LocalDateTime.now()))
    }
}