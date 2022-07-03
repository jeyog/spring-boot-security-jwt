package com.example.springbootsecurityjwt.entity

import com.example.springbootsecurityjwt.dto.JwtUserDTO

class JwtUser(
    val name: String,
    val phone: String,
) {
    constructor(jwtUserDTO: JwtUserDTO): this(
        name = jwtUserDTO.name,
        phone = jwtUserDTO.phone
    )

    override fun toString(): String {
        return "JwtUser(name=$name, phone=$phone)"
    }
}