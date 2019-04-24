package com.asemiashkin

import org.jetbrains.exposed.sql.Table


object Links: Table("links") {
    val id = long("id").primaryKey().autoIncrement()
    val title = varchar("title", length = 100)
    val link = varchar("link", length = 500)
    val userId = (long("user_id") references Users.id)
}

object Users: Table("users") {
    val id = long("id").primaryKey()
    val nickname = varchar("nickname", length = 50)
    val email = varchar("email", length = 50)
    val role = varchar("role", length = 15)
}