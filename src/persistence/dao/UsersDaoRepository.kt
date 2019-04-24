package com.asemiashkin.persistence.dao

import com.asemiashkin.User
import com.asemiashkin.persistence.UsersRepository
import org.jetbrains.exposed.sql.transactions.transaction

class UsersDaoRepository : UsersRepository {
    override fun create(toAdd: User): Long = transaction {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun update(toUpdate: User): Boolean = transaction {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
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