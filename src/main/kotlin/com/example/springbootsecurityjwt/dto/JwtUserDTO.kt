package com.example.springbootsecurityjwt.dto

import com.example.springbootsecurityjwt.entity.JwtUser

data class JwtUserDTO(
    val name: String,
    val phone: String
) {
    constructor(jwtUser: JwtUser): this(
        name = jwtUser.name,
        phone = jwtUser.phone
    )
}