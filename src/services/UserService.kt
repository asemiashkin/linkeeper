package com.asemiashkin.services

import com.asemiashkin.User
import com.asemiashkin.persistence.UsersRepository

class UserService(private val usersRepository: UsersRepository) {

    fun create(link: User): Long {
        return usersRepository.create(link)
    }

    fun update(link: User): Boolean {
        return usersRepository.update(link)
    }

    fun remove(linkId: Long): Boolean {
        return usersRepository.remove(linkId)
    }

    fun fetch(linkId: Long): User {
        return usersRepository.fetch(linkId)
    }

    fun fetchAll(): List<User> {
        return usersRepository.fetchAll()
    }


}