package com.asemiashkin.routings

import com.asemiashkin.*
import com.asemiashkin.services.UserService
import io.ktor.application.call
import io.ktor.http.HttpStatusCode
import io.ktor.request.receive
import io.ktor.response.respond
import io.ktor.routing.*
import java.lang.Exception

fun Routing.usersRest(userService: UserService) = route("/users") {

    get {
        call.respond(userService.fetchAll())
    }

    get("/{id}") {
        val id: Long? = try {
            call.parameters["id"]?.toLong()
        } catch (e: Exception) {
            0
        }
        val user: User? = userService.fetch(id!!)
        if (user == null) call.respond(HttpStatusCode.NotFound)
        call.respond(user!!)
    }

    post {
        call.respond(CreateUserResponse(userService.create(call.receive())))
    }

    put("/{id}") {
        call.respond(UDUserResponse(userService.update(call.receive())))
    }

    delete("/{id}") {
        val id: Long? = try {
        call.parameters["id"]?.toLong()
    } catch (e: Exception) {
        0
    }
        call.respond(UDUserResponse(userService.remove(id!!)))
    }
}