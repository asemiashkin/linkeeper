package com.asemiashkin.persistence.dao

import com.asemiashkin.Role
import com.asemiashkin.User
import com.asemiashkin.Users
import com.asemiashkin.persistence.UsersRepository
import org.jetbrains.exposed.sql.*
import org.jetbrains.exposed.sql.SqlExpressionBuilder.eq
import org.jetbrains.exposed.sql.transactions.transaction
import java.net.URI

class UsersDaoRepository : UsersRepository {
    override fun create(toAdd: User): Long = transaction {
        (Users.insert {
            it[nickname] = toAdd.nickname
            it[email] = toAdd.email
            it[role] = toAdd.role.toString()
        } get Users.id)!!
    }

    override fun update(toUpdate: User): Boolean = transaction {
        Users.update({Users.id eq toUpdate.id}) {
            it[nickname] = toUpdate.nickname
            it[email] = toUpdate.email
            it[role] = toUpdate.role.toString()
        } > 0
    }

    override fun remove(id: Long): Boolean = transaction {
        Users.deleteWhere { Users.id eq id } > 0
    }

    override fun fetch(id: Long): User = transaction {
        Users.select { Users.id eq id }.first().toUser()
    }

    override fun fetchAll(): List<User> = transaction {
        Users.selectAll().toList().map { it.toUser() }
    }

    private fun ResultRow.toUser(): User = User(
        id = this[Users.id],
        nickname = this[Users.nickname],
        email = this[Users.email],
        role = Role.valueOf(this[Users.role])
    )

}