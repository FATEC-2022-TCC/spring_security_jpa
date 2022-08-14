package com.security.database.demo.security

import io.jsonwebtoken.Jwts
import io.jsonwebtoken.SignatureAlgorithm
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Component
import java.security.Key
import java.time.Instant
import java.util.*
import javax.crypto.spec.SecretKeySpec


@Component
class JwtTokenBuilder(
    @Value("\${spring.jwt.secret}")
    private val secret: String,

    @Value("\${spring.jwt.expiration}")
    private val expiration: Long
) {
    val key: Key = SecretKeySpec(
        Base64.getDecoder().decode(secret),
        SignatureAlgorithm.HS256.jcaName
    )

    fun generateToken(username: String, password: String): String {
        return Jwts.builder()
            .setSubject(username)
            .setIssuedAt(Date())
            .setId(UUID.randomUUID().toString())
            .setExpiration(Date.from(Instant.now().plusMillis(expiration)))
            .signWith(key)
            .compact()
    }

    fun parseToken(token: String): Boolean {
        val jwtParser = Jwts.parserBuilder().setSigningKey(key).build()
        return jwtParser.isSigned(token)
    }
}