package com.asemiashkin

import java.net.URI

data class Link(
    var id: Long,
    var title: String,
    var link: URI,
    var userId: Long
)

data class CreateLinkResponse(val id: Long)
data class UDLinkResponse(val successful: Boolean)

data class User(
    var id: Long,
    var nickname: String,
    var email: String,
    var role: Role
)

data class CreateUserResponse(val id: Long)
data class UDUserResponse(val successful: Boolean)

enum class Role {
    ADMIN, USER
}
