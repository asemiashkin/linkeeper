package com.asemiashkin.services

import com.asemiashkin.User
import com.asemiashkin.persistence.UsersRepository

class UserService(private val usersRepository: UsersRepository) {

    fun create(user: User): Long {
        return usersRepository.create(user)
    }

    fun update(user: User): Boolean {
        return usersRepository.update(user)
    }

    fun remove(userId: Long): Boolean {
        return usersRepository.remove(userId)
    }

    fun fetch(userId: Long): User {
        return usersRepository.fetch(userId)
    }

    fun fetchAll(): List<User> {
        return usersRepository.fetchAll()
    }


}