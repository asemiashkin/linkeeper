package com.asemiashkin.persistence.dao

import com.asemiashkin.User
import com.asemiashkin.Users
import com.asemiashkin.persistence.UsersRepository
import org.jetbrains.exposed.sql.insert
import org.jetbrains.exposed.sql.transactions.transaction
import org.jetbrains.exposed.sql.update

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
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun fetch(id: Long): User = transaction {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun fetchAll(): List<User> = transaction {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

}