package com.asemiashkin

import com.asemiashkin.exceptions.LinkeeperException
import io.ktor.application.call
import io.ktor.features.StatusPages
import io.ktor.http.HttpStatusCode
import io.ktor.response.respond

fun StatusPages.Configuration.handleExceptions() {
    exception<Throwable> {
        val status = when (it) {
            is LinkeeperException -> it.statusCode
            else -> HttpStatusCode.InternalServerError
        }
        call.response.status(status)
        call.respond(it)
    }
}
